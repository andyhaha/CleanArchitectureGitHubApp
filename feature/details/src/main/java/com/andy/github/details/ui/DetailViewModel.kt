package com.andy.github.details.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andy.github.details.domain.model.Repository
import com.andy.github.details.domain.model.User
import com.andy.github.details.domain.repository.UserDetailsRepository
import com.andy.network.domain.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class DetailViewModel @Inject constructor(
    private val userRepository: UserDetailsRepository,
) : ViewModel() {

    private val usernameFlow = MutableStateFlow<String?>(null)
    private val retryTrigger = MutableSharedFlow<Unit>(
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST,
    )

    private val refreshSignal: Flow<String> = combine(
        usernameFlow.filterNotNull(),
        retryTrigger.onStart { emit(Unit) },
    ) { name, _ -> name }

    private val userUiState: StateFlow<UserUiState> = refreshSignal
        .flatMapLatest { name ->
            userRepository.getUser(name)
                .map { it.toUserUiState() }
                .onStart { emit(UserUiState.Loading) }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = UserUiState.Loading,
        )

    private val repositoriesUiState: StateFlow<RepositoriesUiState> = refreshSignal
        .flatMapLatest { name ->
            userRepository.getUserRepositories(name)
                .map { it.toRepositoriesUiState() }
                .onStart { emit(RepositoriesUiState.Loading) }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = RepositoriesUiState.Loading,
        )

    val combinedUiState: StateFlow<DetailUiState> = combine(
        userUiState,
        repositoriesUiState,
    ) { userState, repositoriesState ->
        when {
            userState is UserUiState.Success && repositoriesState is RepositoriesUiState.Success ->
                DetailUiState.Success(
                    UserWithRepositories(userState.user, repositoriesState.repositories)
                )

            userState is UserUiState.Error || repositoriesState is RepositoriesUiState.Error ->
                DetailUiState.Error

            else -> DetailUiState.Loading
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = DetailUiState.Loading,
    )

    fun load(username: String) {
        usernameFlow.value = username
    }

    fun retry() {
        retryTrigger.tryEmit(Unit)
    }

    private fun Result<User>.toUserUiState(): UserUiState = when (this) {
        is Result.Success -> UserUiState.Success(value)
        is Result.Error, is Result.Failure -> UserUiState.Error
    }

    private fun Result<List<Repository>>.toRepositoriesUiState(): RepositoriesUiState =
        when (this) {
            is Result.Success -> RepositoriesUiState.Success(value)
            is Result.Error, is Result.Failure -> RepositoriesUiState.Error
        }
}

sealed interface UserUiState {
    data class Success(val user: User) : UserUiState
    data object Error : UserUiState
    data object Loading : UserUiState
}

sealed interface RepositoriesUiState {
    data class Success(val repositories: List<Repository>) : RepositoriesUiState
    data object Error : RepositoriesUiState
    data object Loading : RepositoriesUiState
}

sealed interface DetailUiState {
    data class Success(val userWithRepositories: UserWithRepositories) : DetailUiState
    data object Error : DetailUiState
    data object Loading : DetailUiState
}

data class UserWithRepositories(
    val user: User,
    val repositories: List<Repository>,
)
