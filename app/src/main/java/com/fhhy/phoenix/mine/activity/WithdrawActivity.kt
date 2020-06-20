package com.fhhy.phoenix.mine.activity

import com.fhhy.phoenix.R
import com.fhhy.phoenix.base.BaseMvpActivity
import com.fhhy.phoenix.mine.activity.FundsAccountDetailActivity.Companion.FUNDS_NAME
import com.fhhy.phoenix.mine.contract.WithdrawContract
import com.fhhy.phoenix.mine.presenter.WithdrawPresenter
import kotlinx.android.synthetic.main.activity_funds_account_detail.*
import kotlinx.android.synthetic.main.activity_funds_account_detail.tvTitle
import kotlinx.android.synthetic.main.activity_withdraw.*

// Created by admin on 2020/6/20.
class WithdrawActivity : BaseMvpActivity<WithdrawContract.View, WithdrawContract.Presenter>(),
    WithdrawContract.View {
    override fun createPresenter(): WithdrawContract.Presenter = WithdrawPresenter()

    override fun getLayoutId(): Int = R.layout.activity_withdraw

    override fun initView() {
        super.initView()
        val title = intent.getStringExtra(FUNDS_NAME)
        tvTitle.text = String.format(resources.getString(R.string.coin_withdraw), title)
        initWithdrawWayRB()
    }

    private fun initWithdrawWayRB(){
        rgWithdrawWay.setOnCheckedChangeListener { group, checkedId ->

        }
    }
}