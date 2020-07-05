package com.fhhy.phoenix.utils

import android.annotation.SuppressLint
import android.content.Context
import android.telecom.TelecomManager
import android.telephony.TelephonyManager
import android.text.TextUtils
import kotlinx.android.synthetic.main.activity_main.view.*
import java.lang.Exception

// Created by admin on 2020/6/26.
object DeviceUtils {

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    fun dp2px(context: Context, dpValue: Int): Int {
        val scale: Float = context.resources.displayMetrics.density
        return (dpValue * scale + 0.5f).toInt()
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    fun px2dp(context: Context, pxValue: Int): Int {
        val scale: Float = context.resources.displayMetrics.density
        return (pxValue / scale + 0.5f).toInt()
    }


    //IMEI（imei）
    @SuppressLint("HardwareIds")
    fun getDeviceId(context: Context): String {
        return try {
            val telephonyManager =
                context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
            val deviceId = telephonyManager.deviceId
            if (!TextUtils.isEmpty(deviceId)) {
                deviceId
            } else {
                ""
            }
        } catch (e: Exception) {
            ""
        }

    }
}