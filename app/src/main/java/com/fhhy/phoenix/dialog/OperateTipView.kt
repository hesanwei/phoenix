package com.fhhy.phoenix.dialog

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.fhhy.phoenix.R
import kotlinx.android.synthetic.main.dialog_operate_tip.view.*

// Created by admin on 2020/7/5.
class OperateTipView: RelativeLayout{
    constructor(context: Context) : this(context, null)
    constructor(context: Context, attributeSet: AttributeSet?) : this(context, attributeSet, 0)
    constructor(context: Context, attributeSet: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attributeSet,
        defStyleAttr
    ){
        init(context)
    }

    private fun init(context: Context) {
        val view = View.inflate(context, R.layout.dialog_operate_tip, null)
        addView(view)
    }

    fun setText(text: String?) {
        tvTips!!.text = text
    }
}