package com.mironov.myapplication.api.`interface`

import com.mironov.myapplication.api.`interface`.ClientDto.AuthData
import com.mironov.myapplication.api.`interface`.ClientDto.UserData
import com.mironov.myapplication.api.`interface`.ClientDto.UsernameData
import retrofit2.Call
import retrofit2.http.*
import javax.security.auth.callback.Callback

data class ResponseMessage(val message: String)

interface ApiInterface {
    @POST("/api/v1/registration")
    fun registration(@Body userData: UserData) : Call<String>;

    @POST("/api/v1/login")
    fun login(@Body userData: UserData) : Call<AuthData>;

    @PUT("api/v1/user/set-username")
    fun setUsername(@Body usernameData: UsernameData) : Call<String>

    @GET("api/v1/user/get-username")
    fun getUsername() : Call<String>
}