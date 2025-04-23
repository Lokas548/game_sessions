package com.mironov.myapplication.api.`interface`.ClientService

import com.mironov.myapplication.api.`interface`.ApiInterface
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private const val BASE_URL =
        "http://10.0.2.2:8080"

    fun createRetrofit(authToken: String): Retrofit {
        val client = OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor(authToken))
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getApiService(authToken: String): ApiInterface {
        val retrofit = createRetrofit(authToken)
        println(retrofit)
        return retrofit.create(ApiInterface::class.java)
    }

    fun getNoTokenApiService(): ApiInterface {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(createOkHttpClient())
            .build()

        return retrofit.create(ApiInterface::class.java)
    }

    private fun createOkHttpClient(token: String? = null): OkHttpClient {
        val builder = OkHttpClient.Builder()

        token?.let {
            builder.addInterceptor { chain ->
                val original = chain.request()
                val request = original.newBuilder()
                    .header("Authorization", "Bearer $token")
                    .method(original.method, original.body)
                    .build()
                chain.proceed(request)
            }
        }


        return builder.build()
    }
}