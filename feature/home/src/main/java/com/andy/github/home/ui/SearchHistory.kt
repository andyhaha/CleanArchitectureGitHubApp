package com.andy.github.home.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.andy.github.home.domain.model.SearchItem

@Composable
fun SearchHistory(
    onItemClick: (SearchItem) -> Unit = {},
    delete: (SearchItem) -> Unit = {},
    state: SearchUiState = SearchUiState.Loading
) {
    when (state) {
        is SearchUiState.Success -> {
            SearchHistoryList(
                onItemClick = onItemClick,
                delete = delete,
                historyItems = state.historyItems
            )
        }

        is SearchUiState.Error -> {
            // Handle error state
        }

        is SearchUiState.Loading -> {
            Text("Loading...")
        }
    }
}

@Preview
@Composable
fun PreviewSearchHistory() {
    SearchHistory()
}