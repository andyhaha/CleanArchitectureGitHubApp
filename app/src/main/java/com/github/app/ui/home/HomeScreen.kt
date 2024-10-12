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
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.github.app.domain.model.User

@Composable
fun HomeScreen(
    searchHistoryViewModel: SearchHistoryViewModel = hiltViewModel(),
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    var searchText by remember { mutableStateOf("") }
    val historyItemsState: SearchUiState by searchHistoryViewModel.searchHistoryItems.collectAsStateWithLifecycle()
    Scaffold(
        topBar = {
            HomeSearchBar(
                searchUiState = historyItemsState,
                onSearch = {
                    searchText = it.content
                    if (searchText.isNotEmpty()) {
                        searchHistoryViewModel.addHistoryItem(searchText)
                        homeViewModel.searchUserRepositories(searchText)
                    }
                },
                delete = {
                    searchHistoryViewModel.deleteHistoryItem(it)
                }
            )
        }
    ) { innerPadding ->
        val items: LazyPagingItems<User> = homeViewModel.searchedUsers.collectAsLazyPagingItems()
        HomeContent(innerPadding, items)
    }
}

@Preview
@Composable
fun PreviewHomeScreen() {
    HomeScreen()
}