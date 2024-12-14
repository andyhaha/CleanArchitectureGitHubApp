package com.andy.github.home.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun SearchHistory(
    onItemClick: (UiSearchItem) -> Unit = {},
    delete: (UiSearchItem) -> Unit = {},
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
            Text(state.message)
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