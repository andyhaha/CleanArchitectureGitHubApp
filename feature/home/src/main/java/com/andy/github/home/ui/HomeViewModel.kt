package com.andy.github.home.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.andy.github.home.domain.model.SimpleUser
import com.andy.github.home.domain.repository.SimpleUserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val userRepository: SimpleUserRepository
): ViewModel() {
    private val _searchedUsers = MutableStateFlow<PagingData<SimpleUser>>(PagingData.empty())
    val searchedUsers = _searchedUsers.asStateFlow()

    fun searchUserRepositories(query: String) {
        viewModelScope.launch {
            userRepository.searchUserRepositories(query)
//                .cachedIn(viewModelScope)
                .catch {
                    Log.d("HomeViewModel", "exception: $it")
                }
                .collect {
                    Log.d("HomeViewModel", "collect: $it")
                    _searchedUsers.value = it
                }
        }
    }
}