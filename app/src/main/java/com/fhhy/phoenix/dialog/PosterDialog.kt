package com.fhhy.phoenix.dialog

import com.fhhy.phoenix.R
import kotlinx.android.synthetic.main.dialog_poster.*

// Created by admin on 2020/7/12.
class PosterDialog : BaseDialog() {
    override fun getLayoutId(): Int = R.layout.dialog_poster

    override fun initView() {
        tvCancel.setOnClickListener { dismiss() }
    }
}