package com.fhhy.phoenix.dialog

import android.graphics.BitmapFactory
import android.graphics.Paint
import android.text.TextUtils
import android.view.Gravity
import androidx.appcompat.widget.AppCompatImageButton
import com.fhhy.phoenix.R
import com.fhhy.phoenix.http.RetrofitManager
import com.jakewharton.rxbinding4.widget.textChanges
import kotlinx.android.synthetic.main.dialog_img_check_code.*
import noDoubleClick
import showToast

// Created by admin on 2020/7/5.
class ImgCheckCodeDialog(private val listener: OnOkListener) : BaseDialog() {
    override fun getLayoutId(): Int = R.layout.dialog_img_check_code

    override fun initView() {
        tvRegain.paint.flags = Paint.UNDERLINE_TEXT_FLAG
        loadImgCheckCode()
        llRegain.setOnClickListener {
            loadImgCheckCode()
        }
        etImgCheckCode.textChanges()
            .subscribe {
                setButtonClickable(btnNext, !it.isNullOrEmpty())
            }
        btnNext.noDoubleClick {
            val imgCheckCode = etImgCheckCode.text.toString()
            if (!TextUtils.isEmpty(imgCheckCode)) {
                listener.onOkClick(imgCheckCode)
                dismiss()
            }
        }
    }

    private fun loadImgCheckCode() {
        val subscribe = RetrofitManager.apiService.requestImgCode()
            .compose(SchedulerUtils.ioToMain())
            .map {
                it.byteStream()
            }
            .map {
                BitmapFactory.decodeStream(it)
            }
            .subscribe({
                ivImgCheckCode.setImageBitmap(it)
            }, {
                showToast(it.message)
            })
    }

    /**
     * 设置下一步按钮是否可点击
     */
    private fun setButtonClickable(button: AppCompatImageButton, isClickable: Boolean) {
        button.isClickable = isClickable
        button.setImageResource(if (isClickable) R.drawable.ic_login_next_enable else R.drawable.ic_login_next_disable)
    }

    override fun getGravity(): Int? = Gravity.TOP

    interface OnOkListener {
        fun onOkClick(imgCheckCode: String)
    }
}