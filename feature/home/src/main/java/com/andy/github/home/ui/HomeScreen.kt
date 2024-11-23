package com.andy.github.home.ui

import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
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
import com.andy.github.home.domain.model.SimpleUser

@Composable
fun HomeScreen(
    searchHistoryViewModel: SearchHistoryViewModel = hiltViewModel(),
    homeViewModel: HomeViewModel = hiltViewModel(),
    onSearchListItemClick: (SimpleUser) -> Unit = {}
) {
    var searchText by remember { mutableStateOf("") }
    val historyItemsState: SearchUiState by searchHistoryViewModel.searchHistoryItems.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }
    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            HomeSearchBar(
                snackbarHostState = snackbarHostState,
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
        val items: LazyPagingItems<SimpleUser> = homeViewModel.searchedUsers.collectAsLazyPagingItems()
        HomeContent(
            snackbarHostState = snackbarHostState,
            innerPadding = innerPadding,
            items = items,
            onSearchListItemClick = onSearchListItemClick
        )
    }
}

@Preview
@Composable
fun PreviewHomeScreen() {
    HomeScreen()
}