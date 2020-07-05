package com.fhhy.phoenix.dialog

import android.text.TextUtils
import android.view.Gravity
import androidx.appcompat.widget.AppCompatImageButton
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.fhhy.phoenix.R
import com.fhhy.phoenix.constants.Constants
import com.jakewharton.rxbinding4.widget.textChanges
import kotlinx.android.synthetic.main.dialog_img_check_code.*
import noDoubleClick

// Created by admin on 2020/7/5.
class ImgCheckCodeDialog(private val listener: OnOkListener) : BaseDialog() {
    override fun getLayoutId(): Int = R.layout.dialog_img_check_code

    override fun initView() {
        loadImgCheckCode()
        ivImgCheckCode.setOnClickListener {
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
        Glide.with(activity!!)
            .load(Constants.IMG_CHECK_CODE_URL)
            .skipMemoryCache(true)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .into(ivImgCheckCode)
    }

    /**
     * 设置下一步按钮是否可点击
     */
    private fun setButtonClickable(button: AppCompatImageButton, isClickable: Boolean) {
        button.isClickable = isClickable
        button.setImageResource(if (isClickable) R.drawable.ic_login_next_enable else R.drawable.ic_login_next_disable)
    }

    override fun getGravity(): Int? = Gravity.CENTER

    interface OnOkListener {
        fun onOkClick(imgCheckCode: String)
    }
}