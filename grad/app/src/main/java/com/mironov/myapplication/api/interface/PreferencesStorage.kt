package com.mironov.myapplication.api.`interface`

import android.content.Context
import android.content.SharedPreferences

class PreferencesHelper(context: Context) {
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)

    //Ключ для токена
    private val JWT_TOKEN_KEY = "jwt_token"

    //Сохранение токена
    fun saveJwtToken(token: String) {
        val editor = sharedPreferences.edit()
        editor.putString(JWT_TOKEN_KEY, token)
        editor.apply()
    }

    //Получение токена
    fun getJwtToken(): String? {
        return sharedPreferences.getString(JWT_TOKEN_KEY, null)
    }

    //Удаление токена
    fun clearJwtToken() {
        val editor = sharedPreferences.edit()
        editor.remove(JWT_TOKEN_KEY)
        editor.apply()
    }
}
