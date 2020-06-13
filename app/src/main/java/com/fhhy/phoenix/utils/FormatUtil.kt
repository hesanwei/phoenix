package com.fhhy.phoenix.utils

import java.math.RoundingMode
import java.text.NumberFormat

object FormatUtil {

    fun getNumberFormat(): NumberFormat = NumberFormat.getNumberInstance().apply {
        maximumFractionDigits = 2
        /*
         * setMinimumFractionDigits设置成2
         * 如果不这么做，那么当value的值是100.00的时候返回100
         * 而不是100.00
         */
        minimumFractionDigits = 2
        roundingMode = RoundingMode.HALF_UP
        //无需逗号隔开
        isGroupingUsed =false
    }

}