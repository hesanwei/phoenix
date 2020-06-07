package com.fhhy.phoenix.base

import android.app.Application
import android.content.Context

// Created by admin on 2020/6/7.
object BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
    }

    fun getAppContext():Context{
        return applicationContext
    }
}