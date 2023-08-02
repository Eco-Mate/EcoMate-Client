package com.example.ecomate.common

import android.content.Context
import android.content.SharedPreferences
import com.example.ecomate.ApplicationClass.Companion.ACCESS_TOKEN
import com.example.ecomate.ApplicationClass.Companion.REFRESH_TOKEN

class SharedPreferences(context: Context) {
    private var preferences: SharedPreferences =
        context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)

    fun addAccessToken(accessToken: String) {
        val editor = preferences.edit()
        editor.putString(ACCESS_TOKEN, accessToken)
        editor.apply()
    }

    fun addRefreshToken(refreshToken: String) {
        val editor = preferences.edit()
        editor.putString(REFRESH_TOKEN, refreshToken)
        editor.apply()
    }

    fun getAccessToken(): String {
        return preferences.getString(ACCESS_TOKEN, "").toString()
    }

    fun getRefreshToken(): String {
        return preferences.getString(REFRESH_TOKEN, "").toString()
    }

    fun deleteToken() {
        val editor = preferences.edit()
        editor.remove(ACCESS_TOKEN)
        editor.remove(REFRESH_TOKEN)
        editor.apply()
    }

    companion object {
        private const val SHARED_PREFERENCES_NAME = "ecoMate"
    }
}