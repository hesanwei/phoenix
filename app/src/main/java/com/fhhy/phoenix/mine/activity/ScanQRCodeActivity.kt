package com.fhhy.phoenix.mine.activity

import android.content.Context
import android.content.Intent
import android.os.Vibrator
import android.util.Log
import cn.bingoogolapple.qrcode.core.QRCodeView
import com.fhhy.phoenix.R
import com.fhhy.phoenix.base.BaseActivity
import com.jaeger.library.StatusBarUtil
import kotlinx.android.synthetic.main.activity_scan_qrcode.*

/**
 * Created by hecuncun on 2020/6/22
 * 二维码扫描页
 */
class ScanQRCodeActivity : BaseActivity(), QRCodeView.Delegate {
    private var isOpen = false
    override fun getLayoutId(): Int = R.layout.activity_scan_qrcode
    override fun setStatusBar(): Boolean = false

    override fun initView() {
        StatusBarUtil.setTransparentForImageView(this, btnBack)
        zxView.setDelegate(this)
        zxView.changeToScanQRCodeStyle()
        iv_light.setOnClickListener {
            isOpen = !isOpen
            if (isOpen) {
                zxView.openFlashlight()
            } else {
                zxView.closeFlashlight()
            }

        }

        btnBack.setOnClickListener {
            finish()
        }
    }

    override fun onScanQRCodeSuccess(result: String?) {
        Log.e("QRCode", "result==$result")
        vibrate()
        val resultIntent = Intent()
        resultIntent.putExtra("result", result)
        setResult(RESULT_OK, resultIntent)
        finish()
    }

    private fun vibrate() {
        val vibrator =
            getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        vibrator.vibrate(200)
    }

    override fun onCameraAmbientBrightnessChanged(isDark: Boolean) {
    }

    override fun onScanQRCodeOpenCameraError() {
    }

    override fun onStart() {
        super.onStart()
        zxView.startCamera()
        zxView.startSpotAndShowRect()
    }

    override fun onStop() {
        zxView.stopCamera()
        super.onStop()
    }

    override fun onDestroy() {
        zxView.onDestroy()
        super.onDestroy()
    }
}