package com.fhhy.phoenix.mine.activity

import android.view.View
import com.fhhy.phoenix.R
import com.fhhy.phoenix.base.BaseMvpActivity
import com.fhhy.phoenix.dialog.RechargeWithdrawGuideDialog
import com.fhhy.phoenix.mine.contract.RechargeContract
import com.fhhy.phoenix.mine.presenter.RechargePresenter
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
        tvTitle.text = String.format(resources.getString(R.string.coin_withdraw), title)
        tvRechargeAddress.text =
            String.format(resources.getString(R.string.coin_recharge_address), title)

        initRadioGroup()
        setViewClickListener(this, btnBack, tvCopy)

        RechargeWithdrawGuideDialog().show(supportFragmentManager)
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