package com.fhhy.phoenix.dialog

import android.view.Gravity
import android.view.WindowManager
import com.fhhy.phoenix.R

// Created by admin on 2020/6/7.
object LoadingDialog: BaseDialog() {
    override fun getLayoutId(): Int = R.layout.dialog_loading

    override fun initView() {
        dialog?.window?.clearFlags( WindowManager.LayoutParams.FLAG_DIM_BEHIND);
    }

    override fun getGravity(): Int? = Gravity.CENTER
}