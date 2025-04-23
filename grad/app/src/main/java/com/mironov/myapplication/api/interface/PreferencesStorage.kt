package com.mironov.myapplication.api.`interface`

import android.content.Context
import android.content.SharedPreferences

class PreferencesHelper(context: Context) {
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)


    private val JWT_TOKEN_KEY = "jwt_token"

    fun saveJwtToken(token: String) {
        clearJwtToken()
        sharedPreferences.edit().putString(JWT_TOKEN_KEY, token).apply()
    }


    fun getJwtToken(): String? {

        val token = sharedPreferences.getString(JWT_TOKEN_KEY, null)

        if (token != null) {
            val startIndex: Int = token.indexOf("jwtToken=") + "jwtToken=".length
            println(token.substring(startIndex))
            return token.substring(startIndex, token.length - 1)
        }

        return null
    }


    //Удаление токена
    fun clearJwtToken() {
        val editor = sharedPreferences.edit()
        editor.remove(JWT_TOKEN_KEY)
        editor.apply()
    }
}
