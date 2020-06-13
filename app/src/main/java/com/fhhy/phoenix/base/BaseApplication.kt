package com.fhhy.phoenix.base

import android.content.Context
import androidx.multidex.MultiDexApplication
import com.fhhy.phoenix.R
import com.scwang.smart.refresh.footer.ClassicsFooter
import com.scwang.smart.refresh.header.ClassicsHeader
import com.scwang.smart.refresh.layout.SmartRefreshLayout

// Created by admin on 2020/6/7.
class BaseApplication : MultiDexApplication() {

    init {
        initSmartRefreshLayoutHeader()
    }

    override fun onCreate() {
        super.onCreate()
        sContext = applicationContext
    }

    private fun initSmartRefreshLayoutHeader() {
        SmartRefreshLayout.setDefaultRefreshHeaderCreator { context, layout ->
            layout.setPrimaryColorsId(R.color.white, android.R.color.black);//全局设置主题颜色
            ClassicsHeader(context)//.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header
        }

        SmartRefreshLayout.setDefaultRefreshFooterCreator { context, layout ->
            layout.setPrimaryColorsId(R.color.white, android.R.color.black);//全局设置主题颜色
            ClassicsFooter(context);//.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header
        }
    }

    companion object {
        private var sContext: Context? = null
        fun getAppContext(): Context {
            return sContext!!
        }
    }
}