package com.andy.github.home.data.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.andy.github.home.data.model.toDomainUser
import com.andy.github.home.data.remote.HomeApiService
import com.andy.github.home.domain.model.SimpleUser
import com.andy.network.common.ApiException
import com.andy.network.common.Constants
import com.andy.network.common.messageResId
import com.andy.network.data.ApiResult

class SearchPagingSource(
    private val apiService: HomeApiService,
    private val query: String
) : PagingSource<Int, SimpleUser>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SimpleUser> {
        val page = params.key ?: 1
        return when (val result = apiService.searchUserRepositories(
            query = query,
            perPage = Constants.PAGE_SIZE,
            page = page
        )) {
            is ApiResult.Success -> {
                val list = result.data.items.map {
                    it.toDomainUser()
                }
                LoadResult.Page(
                    data = list,
                    prevKey = if (page == 1) null else page - 1,
                    nextKey = if (list.isEmpty()) null else page + 1
                )
            }

            is ApiResult.Error -> {
                Log.e(
                    "SearchPagingSource", "error code = ${result.code}, " +
                            "message = ${result.message}"
                )
                LoadResult.Error(
                    ApiException(messageResId = result.messageResId(), message = result.message)
                )
            }

            is ApiResult.Exception -> {
                LoadResult.Error(result.throwable)
            }
        }
    }

    override fun getRefreshKey(state: PagingState<Int, SimpleUser>): Int? {
        return state.anchorPosition?.let { position ->
            state.closestPageToPosition(position)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(position)?.nextKey?.minus(1)
        }
    }
}