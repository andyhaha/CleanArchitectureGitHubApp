package com.github.app.ui.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.github.app.domain.model.User
import com.github.app.domain.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val userRepository: HomeRepository
): ViewModel() {
    private val _searchedUsers = MutableStateFlow<PagingData<User>>(PagingData.empty())
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