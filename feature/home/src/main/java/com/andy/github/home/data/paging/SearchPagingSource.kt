package com.andy.github.home.data.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.andy.common.toJson
import com.andy.github.home.data.model.toDomainUser
import com.andy.github.home.data.remote.HomeApiService
import com.andy.github.home.domain.model.SimpleUser
import com.andy.network.common.Constants
import com.andy.network.common.errorMessage
import com.andy.network.data.ApiResult
import com.andy.network.domain.Result
import retrofit2.HttpException
import java.io.IOException

class SearchPagingSource(
    private val apiService: HomeApiService,
    private val query: String
) : PagingSource<Int, SimpleUser>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SimpleUser> {
        val page = params.key ?: 1
        return try {
            val result = apiService.searchUserRepositories(
                query = query,
                perPage = Constants.PAGE_SIZE,
                page = page
            )
            when (result) {
                is ApiResult.Success -> {
                    val list = result.data.items.map {
                        it.toDomainUser()
                    }
                    Log.d(
                        "SearchPagingSource",
                        "list = ${result.data.items.toJson()}"
                    )
                    Result.Success(list)
                    return LoadResult.Page(
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
                    return LoadResult.Error(Throwable(result.errorMessage()))
                }

                is ApiResult.Exception -> {
                    return LoadResult.Error(Throwable(result.throwable))
                }
            }
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, SimpleUser>): Int? {
        return state.anchorPosition?.let { position ->
            state.closestPageToPosition(position)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(position)?.nextKey?.minus(1)
        }
    }
}