package com.fhhy.phoenix.dialog

import android.view.Gravity
import android.view.View
import com.fhhy.phoenix.R
import kotlinx.android.synthetic.main.dialog_recharge_withdraw_guide.*
import setViewClickListener
import showToast

// Created by admin on 2020/6/20.
class RechargeWithdrawGuideDialog : BaseDialog(), View.OnClickListener {
    override fun getLayoutId(): Int = R.layout.dialog_recharge_withdraw_guide

    override fun initView() {
        setViewClickListener(this, tvCancel, tvOk, tvUserGuide)
    }

    override fun getGravity(): Int? = Gravity.CENTER
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tvCancel, R.id.tvOk -> dismiss()

            R.id.tvUserGuide -> {
                showToast("跳转H5")
            }
        }
    }
}