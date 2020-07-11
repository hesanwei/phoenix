package com.fhhy.phoenix.dialog

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.fhhy.phoenix.R

// Created by admin on 2020/6/7.
object LoadingDialog{
    private var mDialog: Dialog? = null

    fun show(context: Context) {
        // 判断是否可以显示dialog
        if (checkCanShow(context)) {
            return
        }
        if (mDialog != null && mDialog!!.isShowing) {
            return
        }
        mDialog = Dialog(context, R.style.AlertDialogStyle)
        val view: View = LayoutInflater.from(context)
            .inflate(R.layout.dialog_loading, null)

//        AnimationUtils.startImageAnim(ivProgress)
        mDialog!!.addContentView(
            view,
            ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        )
        mDialog!!.setCanceledOnTouchOutside(false)
        mDialog!!.show()
    }

    fun dismiss() {
        if (mDialog != null && mDialog!!.isShowing) {
            mDialog!!.dismiss()
            mDialog = null
        }
    }

    private fun checkCanShow(context: Context): Boolean {
        val act: Activity
        act = if (context is Activity) {
            context
        } else {
            return true
        }
        val isDestroyed = act.isDestroyed
        val isFinishing = act.isFinishing
        return isDestroyed || isFinishing
    }
}