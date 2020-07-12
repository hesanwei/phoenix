package com.fhhy.phoenix.dialog

import android.graphics.Bitmap
import com.fhhy.phoenix.R
import com.fhhy.phoenix.utils.QRCodeUtils
import kotlinx.android.synthetic.main.dialog_qr_code.*

// Created by admin on 2020/7/12.
class QRCodeDialog : BaseDialog() {
    override fun getLayoutId(): Int = R.layout.dialog_qr_code

    override fun initView() {
        tvCancel.setOnClickListener { dismiss() }

        QRCodeUtils.generateQRCodeBitmap(
            requireContext(),
            "哈哈哈哈",
            154,
            object : QRCodeUtils.GenerateQRSuccessListener {
                override fun onSuccess(bitmap: Bitmap) {
                    ivQRCode.setImageBitmap(bitmap)
                }
            })
    }
}