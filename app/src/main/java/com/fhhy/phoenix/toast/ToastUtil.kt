package com.fhhy.phoenix.toast

import android.view.Gravity
import android.widget.Toast
import com.fhhy.phoenix.base.BaseApplication

// Created by admin on 2020/6/7.
object ToastUtil {

    private val toastView by lazy {
        ToastView(BaseApplication.getAppContext())
    }

    fun show(text: String?) {
        val toast = Toast.makeText(BaseApplication.getAppContext(), text, Toast.LENGTH_SHORT)
        toast.setGravity(Gravity.CENTER, 0, 0)

        toastView.setText(text)
        toast.view = toastView
        toast.show()
    }
}