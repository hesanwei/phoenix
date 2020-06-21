package com.fhhy.phoenix.contractdetail

import android.content.Context
import com.github.fujianlian.klinechart.DataHelper
import com.github.fujianlian.klinechart.KLineEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.nio.charset.Charset
import java.util.*

/**
 * 模拟网络请求
 * Created by tifezh on 2017/7/3.
 */
object DataRequest {

    fun getStringFromAssert(
        context: Context,
        fileName: String
    ): String {
        try {
            val `in` = context.resources.assets.open(fileName)
            val length = `in`.available()
            val buffer = ByteArray(length)
            `in`.read(buffer)
            return String(buffer, 0, buffer.size, Charset.forName("utf-8"))
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return ""
    }

    fun getALL(context: Context): List<KLineEntity> {

        val data =
            Gson().fromJson<List<KLineEntity>>(
                getStringFromAssert(
                    context,
                    "ibm.json"
                ), object : TypeToken<List<KLineEntity?>?>() {}.type
            )
        DataHelper.calculate(data)

        return data
    }

    /**
     * 分页查询
     *
     * @param context
     * @param offset  开始的索引
     * @param size    每次查询的条数
     */
    fun getData(
        context: Context,
        offset: Int,
        size: Int
    ): List<KLineEntity> {
        val all = getALL(context)
        val data: MutableList<KLineEntity> = ArrayList()
        val start = Math.max(0, all!!.size - 1 - offset - size)
        val stop = Math.min(all.size, all.size - offset)
        for (i in start until stop) {
            data.add(all[i])
        }
        return data
    }
}