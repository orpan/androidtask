package com.example.andoridtask.di

import com.example.andoridtask.retrofit.QuoteApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {
    @Singleton
    @Provides
    fun getRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl("https://api.unsplash.com/")
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    @Singleton
    @Provides
    fun getQuoteAPI(retrofit: Retrofit): QuoteApi{
        return retrofit.create(QuoteApi::class.java)
    }
}