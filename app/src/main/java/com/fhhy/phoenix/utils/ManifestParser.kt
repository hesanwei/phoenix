package com.fhhy.phoenix.utils

import android.content.Context
import android.content.pm.PackageManager

object ManifestParser {
    private const val CONFIG_VALUE = "Config"

    /**
     * 获取FileProviderAuthorities
     * @param context
     * @return
     */
    fun getFileProviderAuthorities(context: Context): String {
        try {
            val info = context.packageManager.getPackageInfo(
                context.packageName,
                PackageManager.GET_PROVIDERS
            )
            val providers = info.providers
            for (provider in providers) {
                if (provider.name.contains("FileProvider")) {
                    return provider.authority
                }
            }
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
            return ""
        }
        return ""
    }
}