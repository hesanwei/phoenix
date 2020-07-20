package com.fhhy.phoenix.mine.activity

import com.fhhy.phoenix.R
import com.fhhy.phoenix.base.BaseMvpActivity
import com.fhhy.phoenix.mine.contract.FundsTransferContract
import com.fhhy.phoenix.mine.presenter.FundsTransferPresenter

// Created by admin on 2020/7/20.
class FundsTransferActivity: BaseMvpActivity<FundsTransferContract.View,FundsTransferContract.Presenter>(),FundsTransferContract.View {
    override fun createPresenter(): FundsTransferContract.Presenter = FundsTransferPresenter()

    override fun getLayoutId(): Int = R.layout.activity_funds_transfer

    override fun initView() {
        super.initView()

    }

}