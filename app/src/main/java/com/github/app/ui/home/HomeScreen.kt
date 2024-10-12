package com.github.app.ui.home

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun HomeScreen(
    viewModel: SearchHistoryViewModel = hiltViewModel()
) {
    var searchText by remember { mutableStateOf("") }
    val historyItemsState: SearchUiState by viewModel.searchHistoryItems.collectAsStateWithLifecycle()
    Scaffold(
        topBar = {
            HomeSearchBar(
                searchUiState = historyItemsState,
                onSearch = {
                    searchText = it.content
                    if (searchText.isNotEmpty()) {
                        viewModel.addHistoryItem(searchText)
                    }
                },
                delete = {
                    viewModel.deleteHistoryItem(it)
                }
            )
        }
    ) { innerPadding ->
        HomeContent(innerPadding, searchText)
    }
}

@Preview
@Composable
fun PreviewHomeScreen() {
    HomeScreen()
}