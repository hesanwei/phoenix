package com.fhhy.phoenix.mine.activity

import com.fhhy.phoenix.R
import com.fhhy.phoenix.base.BaseActivity
import kotlinx.android.synthetic.main.activity_funds_record_detail.*

// Created by admin on 2020/6/14.
class FundsRecordDetailActivity : BaseActivity() {
    override fun getLayoutId(): Int = R.layout.activity_funds_record_detail

    override fun initView() {
        btnBack.setOnClickListener { finish() }
        tvCurrency.text = "USDT"
        tvMoney.text = "-20.00"
        tvStatus.text = "已确认"
        tvRecordTypeValue.text = "资金划转-转出"
        tvServiceFeeValue.text = "0.00 USDT"
        tvCreateTimeValue.text = "2020.05.31 12:36 "
    }
}