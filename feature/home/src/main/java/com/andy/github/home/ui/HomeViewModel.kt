package com.andy.github.home.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.andy.github.home.domain.model.SimpleUser
import com.andy.github.home.domain.repository.SimpleUserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val userRepository: SimpleUserRepository,
) : ViewModel() {

    private val query = MutableStateFlow<String?>(null)

    val searchedUsers: StateFlow<PagingData<SimpleUser>> = query
        .filterNotNull()
        .flatMapLatest { userRepository.searchUserRepositories(it) }
        .cachedIn(viewModelScope)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = PagingData.empty(),
        )

    fun search(query: String) {
        this.query.value = query
    }
}
