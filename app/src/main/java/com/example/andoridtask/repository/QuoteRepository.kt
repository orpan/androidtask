package com.example.andoridtask.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.example.andoridtask.paging.QuotePagingSource
import com.example.andoridtask.retrofit.QuoteApi
import javax.inject.Inject

class QuoteRepository  @Inject constructor(val quoteApi: QuoteApi) {
    fun getQuotes()= Pager(
        config = PagingConfig(pageSize = 10, maxSize = 100),
        pagingSourceFactory = { QuotePagingSource(quoteApi) }
    ).liveData
}