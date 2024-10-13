package com.github.app.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.github.app.domain.model.SimpleUser
import com.github.app.domain.repository.SimpleUserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
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
                .cachedIn(viewModelScope)
                .collect {
                    _searchedUsers.value = it
                }
        }
    }
}