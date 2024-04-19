package com.example.andoridtask.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.andoridtask.model.ImageListItem
import com.example.andoridtask.retrofit.QuoteApi

class QuotePagingSource (private val quoteAPI: QuoteApi) : PagingSource<Int, ImageListItem>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ImageListItem> {
        return try {
            val position = params.key ?: 1
            val response = quoteAPI.getQuotes(position,"7QX3UZ1lARYwejZVJhSKcPNJ7QHBhJgwlSvfByQyuN0")

            return LoadResult.Page(
                data = response,
                prevKey = if (position == 1) null else position - 1,
                nextKey = if (position == 10000) null else position + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, ImageListItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

}