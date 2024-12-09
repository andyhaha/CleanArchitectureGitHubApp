package com.andy.github.home.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andy.github.home.domain.model.SearchItem
import com.andy.github.home.domain.repository.SearchHistoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.lang.Error
import javax.inject.Inject

@HiltViewModel
class SearchHistoryViewModel @Inject constructor(
    private val searchHistoryRepository: SearchHistoryRepository
) : ViewModel() {

    val searchHistoryItems: StateFlow<SearchUiState> = searchHistoryRepository.getAllSearchRecords()
        .map { list ->
            SearchUiState.Success(
                historyItems = list
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

    fun deleteHistoryItem(content: SearchItem) {
        viewModelScope.launch {
            searchHistoryRepository.deleteSearch(
                content
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
    data class Success(val historyItems: List<SearchItem>) : SearchUiState
    data class Error(val message: String) : SearchUiState
    data object Loading : SearchUiState
}