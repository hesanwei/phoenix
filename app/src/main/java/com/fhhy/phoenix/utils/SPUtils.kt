package com.fhhy.phoenix.utils

import android.content.Context
import android.content.SharedPreferences
import com.fhhy.phoenix.base.BaseApplication
import kotlin.reflect.KProperty

// Created by admin on 2020/6/26.
object SPUtils {

    private const val SP_NAME = "sp_name"

    private val sharedPreferences by lazy {
        BaseApplication.getAppContext().getSharedPreferences(SP_NAME, Context.MODE_PRIVATE)
    }


    fun setString(key: String, value: String) {
        sharedPreferences.edit().putString(key, value).apply()
    }

    fun getString(key: String): String {
        val string = sharedPreferences.getString(key, "")

        return string ?: ""
    }

    fun setInt(key: String, value: Int) {
        sharedPreferences.edit().putInt(key, value).apply()
    }

    fun getInt(key: String): Int {
        return sharedPreferences.getInt(key, -1)
    }

    fun setBoolean(key: String, value: Boolean) {
        sharedPreferences.edit().putBoolean(key, value).apply()
    }

    fun getBoolean(key: String): Boolean {
        return sharedPreferences.getBoolean(key, false)
    }
}