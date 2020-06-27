package com.fhhy.phoenix.contract.activity

import android.view.View
import com.fhhy.phoenix.R
import com.fhhy.phoenix.base.BaseActivity
import kotlinx.android.synthetic.main.activity_history_order_detail.*
import setViewClickListener
import showToast

// Created by admin on 2020/6/27.
class HistoryOrderDetailActivity: BaseActivity(), View.OnClickListener {
    override fun getLayoutId(): Int = R.layout.activity_history_order_detail

    override fun initView() {
        setViewClickListener(this,btnBack,tvOrderCode,tvShare)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btnBack -> finish()
            R.id.tvOrderCode -> {
                showToast("复制")
            }

            R.id.tvShare -> {
                showToast("分享")
            }
        }
    }

}