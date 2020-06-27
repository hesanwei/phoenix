package com.fhhy.phoenix.contractdetail.dialog

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager

/**
 * description:全屏且背景透明的dialog
 */
open class FullScreenDialogFragment : DialogFragment() {
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        val mWindow = dialog!!.window
        mWindow!!.requestFeature(Window.FEATURE_NO_TITLE)
        mWindow.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        super.onActivityCreated(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        val dialog = dialog
        val window = dialog!!.window
        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT)) //设置窗口透明
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT) //设置窗口全屏
        dialog.setCancelable(true)
        afterOnStart(dialog, window)
    }

    protected fun afterOnStart(dialog: Dialog?, window: Window?) {}

    override fun show(manager: FragmentManager, tag: String?) {
        try {
            super.show(manager, tag)
        } catch (ignore: Exception) {
            //忽略
        }
    }

    override fun dismiss() {
        dismissAllowingStateLoss()
    }
}