package com.example.andoridtask.retrofit

import com.example.andoridtask.model.ImageList
import retrofit2.http.GET
import retrofit2.http.Query

interface QuoteApi {

    @GET("photos")
    suspend fun getQuotes(@Query("page") page:Int, @Query("client_id") client_id : String) : ImageList
}