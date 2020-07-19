package com.fhhy.phoenix.dialog

import android.view.Gravity
import android.view.View
import com.fhhy.phoenix.R
import kotlinx.android.synthetic.main.dialog_logout.*

// Created by admin on 2020/7/19.
class LogoutDialog(private val listener: View.OnClickListener) : BaseDialog() {
    override fun getLayoutId(): Int = R.layout.dialog_logout

    override fun initView() {
        tvCancel.setOnClickListener { dismiss() }
        tvOk.setOnClickListener(listener)
    }

    override fun getGravity(): Int? = Gravity.CENTER
}