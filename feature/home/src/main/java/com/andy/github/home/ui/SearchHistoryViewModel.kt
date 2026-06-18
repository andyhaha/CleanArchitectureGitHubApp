package com.andy.github.home.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andy.common.UiState
import com.andy.github.home.domain.model.SearchItem
import com.andy.github.home.domain.model.toUiSearchItem
import com.andy.github.home.domain.repository.SearchHistoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchHistoryViewModel @Inject constructor(
    private val searchHistoryRepository: SearchHistoryRepository
) : ViewModel() {

    val viewState: StateFlow<UiState<List<UiSearchItem>>> =
        searchHistoryRepository.getAllSearchRecords()
            .map<List<SearchItem>, UiState<List<UiSearchItem>>> { list ->
                UiState.Success(list.map { it.toUiSearchItem() })
            }
            .catch { emit(UiState.Error(it.message ?: "Unknown Error")) }
            .stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(),
                UiState.Loading
            )

    fun addHistoryItem(content: String) {
        viewModelScope.launch {
            searchHistoryRepository.insertSearch(
                SearchItem(content = content)
            )
        }
    }

    fun deleteHistoryItem(content: UiSearchItem) {
        viewModelScope.launch {
            searchHistoryRepository.deleteSearch(
                content.toDomainSearchItem()
            )
        }
    }

    fun clearHistory() {
        viewModelScope.launch {
            searchHistoryRepository.deleteAllSearchRecords()
        }
    }
}

data class UiSearchItem(
    val id: Int = 0,
    val content: String
) {
    fun toDomainSearchItem() = SearchItem(
        id = id,
        content = content
    )
}