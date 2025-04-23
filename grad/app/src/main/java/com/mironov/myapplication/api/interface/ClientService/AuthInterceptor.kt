package com.mironov.myapplication.api.`interface`.ClientService

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class AuthInterceptor(private val authToken: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        val newRequest = originalRequest.newBuilder()
            .header("Authorization", "Bearer $authToken")
            .build()

        logRequest(newRequest)
        return chain.proceed(newRequest)
    }
}

private fun logRequest(request: Request) {
    println("Request URL: ${request.url}")
    println("Request Method: ${request.method}")
    println("Request Headers: ${request.headers}")

    val authHeader = request.header("Authorization")
    println("Authorization Header: $authHeader")

    request.body?.let { body ->
        val buffer = okio.Buffer()
        body.writeTo(buffer)
        val requestBodyString = buffer.readUtf8()
        println("Request Body: $requestBodyString")
    } ?: println("Request Body: null")
}

