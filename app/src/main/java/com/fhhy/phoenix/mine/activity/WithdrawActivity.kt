package com.fhhy.phoenix.mine.activity

import android.view.View
import com.fhhy.phoenix.R
import com.fhhy.phoenix.base.BaseMvpActivity
import com.fhhy.phoenix.dialog.RechargeWithdrawGuideDialog
import com.fhhy.phoenix.mine.activity.FundsAccountDetailActivity.Companion.FUNDS_NAME
import com.fhhy.phoenix.mine.contract.WithdrawContract
import com.fhhy.phoenix.mine.presenter.WithdrawPresenter
import kotlinx.android.synthetic.main.activity_withdraw.*
import setViewClickListener

// Created by admin on 2020/6/20.
class WithdrawActivity : BaseMvpActivity<WithdrawContract.View, WithdrawContract.Presenter>(),
    WithdrawContract.View, View.OnClickListener {
    override fun createPresenter(): WithdrawContract.Presenter = WithdrawPresenter()

    override fun getLayoutId(): Int = R.layout.activity_withdraw

    override fun initView() {
        super.initView()
        val title = intent.getStringExtra(FUNDS_NAME)
        tvTitle.text = String.format(resources.getString(R.string.coin_withdraw), title)
        initRadioGroup()
        setViewClickListener(this, btnBack, ibScan, tvWithdraw)

        RechargeWithdrawGuideDialog().show(supportFragmentManager)
    }

    private fun initRadioGroup() {
        rgWithdrawWay.check(R.id.rbCommonWithdraw)
        rgWithdrawWay.setOnCheckedChangeListener { group, checkedId ->

        }

        rgChainName.check(R.id.rbChainName1)
        rgChainName.setOnCheckedChangeListener { group, checkedId ->

        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnBack -> finish()

            R.id.ibScan -> {

            }

            R.id.tvWithdraw -> {

            }
        }
    }
}