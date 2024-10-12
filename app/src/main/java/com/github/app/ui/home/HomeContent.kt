package com.github.app.ui.home

import android.util.Log
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.github.app.domain.model.User
import com.github.app.ui.component.LoadError
import com.github.app.ui.component.LoadNoData
import com.github.app.ui.component.LoadingItem
import com.github.app.ui.component.NoMoreDataItem

@Composable
fun HomeContent(
    innerPadding: PaddingValues = PaddingValues(),
    items: LazyPagingItems<User>
) {
    Log.d("HomeContent", "loadState = ${items.loadState}")
    if (items.itemCount == 0) {
        LoadNoData()
        return
    }

    when {
        items.loadState.refresh is LoadState.Error -> {
            LoadError(innerPadding)
        }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(innerPadding),
    ) {
        items(count = items.itemCount) { index ->
            val item = items[index]
            item?.let {
                UserItem(it)
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
                    // showing snack bar here that will be append to the lazy columns
                    // It is not a good practice to do this. so now don't handle the error here
//                    val error = (items.loadState.append as LoadState.Error).error
//                    error.message?.let { ShowSnackBar(it) }
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