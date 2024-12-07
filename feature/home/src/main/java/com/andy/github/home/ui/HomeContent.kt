package com.andy.github.home.ui

import android.util.Log
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.andy.github.home.domain.model.SimpleUser
import com.andy.github.home.ui.components.LoadingItem
import com.andy.github.home.ui.components.NoData
import com.andy.github.home.ui.components.NoMoreDataItem

@Composable
fun HomeContent(
    snackbarHostState: SnackbarHostState,
    innerPadding: PaddingValues = PaddingValues(),
    items: LazyPagingItems<SimpleUser>,
    onSearchListItemClick: (SimpleUser) -> Unit = {}
) {
    Log.d("HomeContent", "items.loadState = ${items.loadState}")
    Log.d("HomeContent", "items.loadState.refresh = ${items.loadState.refresh}")
    when {
        items.loadState.refresh is LoadState.Error -> {
            val error = (items.loadState.refresh as LoadState.Error).error
            LaunchedEffect(snackbarHostState) {
                snackbarHostState.showSnackbar(
                    message = error.message ?: "Unknown error!",
                    actionLabel = "Cancel",
                    duration = SnackbarDuration.Short
                )
            }
        }

        items.loadState.append is LoadState.Error -> {
            Log.d("HomeContent", "items.loadState.append is LoadState.Error: ${items.loadState.append}")
            val error = (items.loadState.append as LoadState.Error).error
            LaunchedEffect(snackbarHostState) {
                snackbarHostState.showSnackbar(
                    message = error.message ?: "Unknown error!",
                    actionLabel = "Cancel",
                    duration = SnackbarDuration.Short
                )
            }
        }
    }

    if (items.itemCount == 0) {
        NoData()
        return
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(innerPadding),
    ) {
        items(count = items.itemCount) { index ->
            val item = items[index]
            item?.let {
                UserItem(it, onSearchListItemClick)
            }
        }
        item {
            when {
                items.loadState.append is LoadState.Loading -> {
                    LoadingItem()
                }

                items.loadState.append.endOfPaginationReached -> {
                    NoMoreDataItem()
                }

                items.loadState.append is LoadState.Error -> {
                    Log.d("HomeContent", "LazyColumn items.loadState.append " +
                            "is LoadState.Error: ${items.loadState.append}")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHomeContent() {
//    HomeContent(PaddingValues())
}