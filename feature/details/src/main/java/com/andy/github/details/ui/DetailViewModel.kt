package com.andy.github.details.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andy.common.UiState
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

    private val userUiState: StateFlow<UiState<User>> = refreshSignal
        .flatMapLatest { name ->
            userRepository.getUser(name)
                .map { it.toUiState() }
                .onStart { emit(UiState.Loading) }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = UiState.Loading,
        )

    private val repositoriesUiState: StateFlow<UiState<List<Repository>>> = refreshSignal
        .flatMapLatest { name ->
            userRepository.getUserRepositories(name)
                .map { it.toUiState() }
                .onStart { emit(UiState.Loading) }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = UiState.Loading,
        )

    val combinedUiState: StateFlow<UiState<UserWithRepositories>> = combine(
        userUiState,
        repositoriesUiState,
    ) { userState, repositoriesState ->
        when {
            userState is UiState.Success && repositoriesState is UiState.Success ->
                UiState.Success(
                    UserWithRepositories(userState.data, repositoriesState.data)
                )

            userState is UiState.Error || repositoriesState is UiState.Error ->
                UiState.Error(
                    (userState as? UiState.Error)?.message
                        ?: (repositoriesState as? UiState.Error)?.message
                )

            else -> UiState.Loading
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = UiState.Loading,
    )

    fun load(username: String) {
        usernameFlow.value = username
    }

    fun retry() {
        retryTrigger.tryEmit(Unit)
    }

    private fun <T> Result<T>.toUiState(): UiState<T> = when (this) {
        is Result.Success -> UiState.Success(value)
        is Result.Error -> UiState.Error(message)
        is Result.Failure -> UiState.Error(throwable?.message)
    }
}

data class UserWithRepositories(
    val user: User,
    val repositories: List<Repository>,
)
