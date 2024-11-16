package com.andy.github.details.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andy.github.details.domain.model.Repository
import com.andy.github.details.domain.model.User
import com.andy.github.details.domain.repository.UserDetailsRepository
import com.andy.network.domain.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val userRepository: UserDetailsRepository,
) : ViewModel() {
    private val _userUiState = MutableStateFlow<UserUiState>(UserUiState.Loading)
    private val _repositoriesUiState =
        MutableStateFlow<RepositoriesUiState>(RepositoriesUiState.Loading)

    val combinedUiState: StateFlow<DetailUiState> = combine(
        _userUiState,
        _repositoriesUiState
    ) { userState, repositoriesState ->
        Log.d(
            "DetailViewModel", "userState = $userState, " +
                    "repositoriesState = $repositoriesState"
        )
        when {
            userState is UserUiState.Success && repositoriesState is RepositoriesUiState.Success -> {
                val userWithRepositories = UserWithRepositories(
                    user = userState.user,
                    repositories = repositoriesState.repositories
                )
                DetailUiState.Success(userWithRepositories)
            }
            userState is UserUiState.Error || repositoriesState is RepositoriesUiState.Error -> {
                DetailUiState.Error
            }
            else -> DetailUiState.Loading
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = DetailUiState.Loading
    )

    fun getUserWithRepositories(
        username: String
    ) {
        getUser(username)
        getUserRepositories(username)
    }

    private fun getUser(username: String) {
        userRepository.getUser(username).onEach {
            _userUiState.value = when (it) {
                is Result.Success -> UserUiState.Success(it.value)
                is Result.Error,
                is Result.Failure -> UserUiState.Error
            }
        }.launchIn(viewModelScope)
    }

    private fun getUserRepositories(username: String) {
        userRepository.getUserRepositories(username).onEach {
            _repositoriesUiState.value = when (it) {
                is Result.Success -> RepositoriesUiState.Success(it.value)
                is Result.Error,
                is Result.Failure -> RepositoriesUiState.Error
            }
        }.launchIn(viewModelScope)
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
    val repositories: List<Repository>
)