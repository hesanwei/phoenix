package com.fhhy.phoenix.mine.activity

import com.fhhy.phoenix.R
import com.fhhy.phoenix.base.BaseMvpActivity
import com.fhhy.phoenix.mine.contract.FullAccountDetailContract
import com.fhhy.phoenix.mine.presenter.FullAccountDetailPresenter

// Created by admin on 2020/6/20.
class FullAccountDetailActivity: BaseMvpActivity<FullAccountDetailContract.View,FullAccountDetailContract.Presenter>(),FullAccountDetailContract.View {
    override fun createPresenter(): FullAccountDetailContract.Presenter = FullAccountDetailPresenter()

    override fun getLayoutId(): Int = R.layout.activity_full_account_detail
}