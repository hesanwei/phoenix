package com.fhhy.phoenix.utils

import android.Manifest
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import cn.bingoogolapple.qrcode.zxing.QRCodeEncoder
import com.fhhy.phoenix.base.BaseActivity
import com.fhhy.phoenix.mine.activity.ScanQRCodeActivity
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import kotlinx.android.synthetic.main.activity_recharge.*

// Created by admin on 2020/6/26.
object QRCodeUtils {
    /**
     * 跳转到扫描二维码页面
     */
    fun jumpToScanActivity(
        activity: BaseActivity,
        REQUEST_CODE: Int,
        PERMISSION_REQUEST_CODE: Int
    ) {
        if (activity.checkPermissions(
                arrayOf(
                    Manifest.permission.CAMERA,
                    Manifest.permission.VIBRATE
                )
            )
        ) {
            val intent = Intent(activity, ScanQRCodeActivity::class.java)
            activity.startActivityForResult(intent, REQUEST_CODE)
        } else {
            activity.requestPermission(
                arrayOf(Manifest.permission.CAMERA, Manifest.permission.VIBRATE),
                PERMISSION_REQUEST_CODE
            )
        }
    }

    /**
     * 生成二维码图片
     */
    fun generateQRCodeBitmap(
        context: Context,
        content: String,
        size: Int,
        listener: GenerateQRSuccessListener
    ) {
        val dis = Observable.create(ObservableOnSubscribe<Bitmap> { emitter ->
            val syncEncodeQRCode =
                QRCodeEncoder.syncEncodeQRCode(content, DeviceUtils.dp2px(context, size))
            emitter.onNext(syncEncodeQRCode)
            emitter.onComplete()
        }).compose(SchedulerUtils.ioToMain())
            .subscribe({
                if (it != null) {
                    listener.onSuccess(it)
                }
            }, {})
    }

    interface GenerateQRSuccessListener {
        fun onSuccess(bitmap: Bitmap)
    }
}