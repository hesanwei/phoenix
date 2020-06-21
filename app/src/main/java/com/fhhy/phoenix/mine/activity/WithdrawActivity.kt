package com.fhhy.phoenix.mine.activity

import android.content.Intent
import android.view.View
import com.fhhy.phoenix.R
import com.fhhy.phoenix.base.BaseMvpActivity
import com.fhhy.phoenix.dialog.RechargeWithdrawGuideDialog
import com.fhhy.phoenix.mine.activity.FundsAccountDetailActivity.Companion.FUNDS_NAME
import com.fhhy.phoenix.mine.contract.WithdrawContract
import com.fhhy.phoenix.mine.presenter.WithdrawPresenter
import kotlinx.android.synthetic.main.activity_withdraw.*
import setViewClickListener
import showToast

// Created by admin on 2020/6/20.
class WithdrawActivity : BaseMvpActivity<WithdrawContract.View, WithdrawContract.Presenter>(),
    WithdrawContract.View, View.OnClickListener {

    private var title: String? = ""

    override fun createPresenter(): WithdrawContract.Presenter = WithdrawPresenter()

    override fun getLayoutId(): Int = R.layout.activity_withdraw

    override fun initView() {
        super.initView()
        title = intent.getStringExtra(FUNDS_NAME)
        tvTitle.text = String.format(resources.getString(R.string.coin_withdraw), title)
        initRadioGroup()
        setViewClickListener(this, btnBack, ibScan, tvWithdraw, ivArrow)

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
                showToast("跳转去扫描")
            }

            R.id.ivArrow -> {
                val intent = Intent(this, CoinAddressActivity::class.java)
                intent.putExtra(FUNDS_NAME, title)
                startActivity(intent)
            }

            R.id.tvWithdraw -> {

            }
        }
    }
}