package com.mironov.myapplication.api.`interface`.ClientService

import com.mironov.myapplication.api.`interface`.ApiInterface
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
   private const val BASE_URL = "http://10.0.2.2:8080" // Используйте 10.0.2.2 для доступа к локальному серверу из эмулятора

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiService: ApiInterface by lazy {
        retrofit.create(ApiInterface::class.java)
    }
}