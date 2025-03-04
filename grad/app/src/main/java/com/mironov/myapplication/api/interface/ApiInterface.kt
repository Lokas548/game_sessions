package com.mironov.myapplication.api.`interface`

import com.mironov.myapplication.api.`interface`.ClientDto.AuthData
import com.mironov.myapplication.api.`interface`.ClientDto.UserData
import retrofit2.http.GET
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST
import javax.security.auth.callback.Callback

data class ResponseMessage(val message: String)

interface ApiInterface {
    @GET("/api/v1/test")
    @Headers("Accept: application/json")
    fun getHelloWorld() : Call<ResponseMessage>;

    @POST("/api/v1/registration")
    fun registration(@Body userData: UserData) : Call<String>;

    @POST("/api/v1/login")
    fun login(@Body userData: UserData) : Call<AuthData>;
}