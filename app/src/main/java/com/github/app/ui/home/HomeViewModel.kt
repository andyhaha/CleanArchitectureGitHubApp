package com.github.app.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.app.domain.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val userRepository: HomeRepository
): ViewModel() {

    fun searchUserRepositories() {
        viewModelScope.launch {
            userRepository.searchUserRepositories().collect {

            }
        }
    }
}