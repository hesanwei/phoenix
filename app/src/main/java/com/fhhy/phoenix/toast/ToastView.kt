package com.fhhy.phoenix.toast

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import com.fhhy.phoenix.R

// Created by admin on 2020/6/7.
class ToastView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {
    private var tvToastText: TextView? = null
    private fun init(context: Context) {
        val view = View.inflate(context, R.layout.toast_view, null)
        tvToastText = view.findViewById(R.id.tvToastText)
        addView(view)
    }

    fun setText(text: String?) {
        tvToastText!!.text = text
    }

    init {
        init(context)
    }
}