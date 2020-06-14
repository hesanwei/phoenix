package com.fhhy.phoenix.mine.widgets

import android.content.Context
import android.text.TextPaint
import android.text.style.ClickableSpan
import android.view.View
import com.fhhy.phoenix.R

// Created by admin on 2020/6/14.
abstract class BlueSpanText(private val context: Context) : ClickableSpan() {

    override fun updateDrawState(ds: TextPaint) {
        super.updateDrawState(ds)
        //设置文本颜色
        ds.color = context.resources.getColor(R.color.blue_button_color)
        ds.isFakeBoldText = true
        ds.isUnderlineText = true
    }
}