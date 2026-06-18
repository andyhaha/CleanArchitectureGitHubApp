package com.andy.github.home.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.andy.github.home.domain.model.SimpleUser
import com.andy.github.home.ui.components.LoadError
import com.andy.network.R
import com.andy.network.common.ApiException
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
    val context = LocalContext.current
    val refreshState = items.loadState.refresh
    val appendState = items.loadState.append

    if (refreshState is LoadState.Error && items.itemCount == 0) {
        val error = refreshState.error
        val message = (error as? ApiException)?.messageResId?.let { stringResource(it) }
            ?: error.message
        LoadError(
            innerPadding = innerPadding,
            message = message,
            onRetry = { items.retry() },
        )
        return
    }

    if (appendState is LoadState.Error) {
        val error = appendState.error
        LaunchedEffect(error) {
            val message = (error as? ApiException)?.messageResId?.let { context.getString(it) }
                ?: error.message
                ?: context.getString(R.string.error_unknown)
            snackbarHostState.showSnackbar(
                message = message,
                actionLabel = "Cancel",
                duration = SnackbarDuration.Short,
            )
        }
    }

    if (items.itemCount == 0 && refreshState !is LoadState.Loading) {
        NoData(innerPadding)
        return
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding),
    ) {
        items(count = items.itemCount) { index ->
            items[index]?.let { UserItem(it, onSearchListItemClick) }
        }
        item {
            when {
                appendState is LoadState.Loading || refreshState is LoadState.Loading -> {
                    LoadingItem()
                }
                appendState.endOfPaginationReached && items.itemCount > 0 -> {
                    NoMoreDataItem()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHomeContent() {
}
