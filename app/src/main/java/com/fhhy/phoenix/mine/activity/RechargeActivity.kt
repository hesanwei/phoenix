package com.fhhy.phoenix.mine.activity

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.view.View
import cn.bingoogolapple.qrcode.zxing.QRCodeDecoder
import cn.bingoogolapple.qrcode.zxing.QRCodeEncoder
import com.fhhy.phoenix.R
import com.fhhy.phoenix.base.BaseMvpActivity
import com.fhhy.phoenix.dialog.RechargeWithdrawGuideDialog
import com.fhhy.phoenix.mine.contract.RechargeContract
import com.fhhy.phoenix.mine.presenter.RechargePresenter
import com.fhhy.phoenix.utils.DeviceUtils
import com.fhhy.phoenix.utils.QRCodeUtils
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.ObservableOnSubscribe
import io.reactivex.Scheduler
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_recharge.*
import kotlinx.android.synthetic.main.activity_recharge.rgChainName
import kotlinx.android.synthetic.main.activity_recharge.tvTitle
import setViewClickListener

// Created by admin on 2020/6/20.
class RechargeActivity : BaseMvpActivity<RechargeContract.View, RechargeContract.Presenter>(),
    RechargeContract.View, View.OnClickListener {
    override fun createPresenter(): RechargeContract.Presenter = RechargePresenter()

    override fun getLayoutId(): Int = R.layout.activity_recharge

    override fun initView() {
        super.initView()
        val title = intent.getStringExtra(FundsAccountDetailActivity.FUNDS_NAME)
        tvTitle.text = String.format(resources.getString(R.string.coin_recharge), title)
        tvRechargeAddress.text =
            String.format(resources.getString(R.string.coin_recharge_address), title)

        initRadioGroup()
        setViewClickListener(this, btnBack, tvCopy)

        RechargeWithdrawGuideDialog().show(supportFragmentManager)

        generateQRBitmap("1DDehooD4vEDeua9eagsuLxG2YzToiwt7")
    }

    private fun generateQRBitmap(content: String) {
        QRCodeUtils.generateQRCodeBitmap(
            this,
            content,
            130,
            listener = object : QRCodeUtils.GenerateQRSuccessListener {
                override fun onSuccess(bitmap: Bitmap) {
                    ivQRCode.setImageBitmap(bitmap)
                }
            })
    }

    private fun initRadioGroup() {
        rgChainName.check(R.id.rbChainName1)
        rgChainName.setOnCheckedChangeListener { group, checkedId ->

        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnBack -> finish()

            R.id.tvCopy -> {

            }
        }
    }
}