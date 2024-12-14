package com.andy.github.home.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

    val viewState: StateFlow<SearchUiState> = searchHistoryRepository.getAllSearchRecords()
        .map { list ->
            SearchUiState.Success(
                historyItems = list.map {
                    it.toUiSearchItem()
                }
            )
        }
        .catch {
            SearchUiState.Error(it.message ?: "Unknown Error")
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(),
            SearchUiState.Loading
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

sealed interface SearchUiState {
    data class Success(val historyItems: List<UiSearchItem>) : SearchUiState
    data class Error(val message: String) : SearchUiState
    data object Loading : SearchUiState
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