package com.fhhy.phoenix.mine.activity

import android.view.View
import com.fhhy.phoenix.R
import com.fhhy.phoenix.base.BaseMvpActivity
import com.fhhy.phoenix.mine.activity.FundsAccountDetailActivity.Companion.FUNDS_NAME
import com.fhhy.phoenix.mine.contract.AddNewAddressContract
import com.fhhy.phoenix.mine.presenter.AddNewsAddressPresenter
import com.jaeger.library.StatusBarUtil
import kotlinx.android.synthetic.main.activity_add_new_address.*
import setViewClickListener

// Created by admin on 2020/6/21.
class AddNewAddressActivity :
    BaseMvpActivity<AddNewAddressContract.View, AddNewAddressContract.Presenter>(),
    AddNewAddressContract.View,
    View.OnClickListener {
    override fun createPresenter(): AddNewAddressContract.Presenter = AddNewsAddressPresenter()

    override fun getLayoutId(): Int = R.layout.activity_add_new_address

    override fun initView() {
        super.initView()
        initData()
        setViewClickListener(this, btnBack, tvAdd, ibScan)
    }

    private fun initData() {
        val fundsName = intent.getStringExtra(FUNDS_NAME)
        tvTitle.text = String.format(resources.getString(R.string.add_coin_address), fundsName)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnBack -> finish()

            R.id.tvAdd -> {

            }

            R.id.ibScan -> {

            }
        }
    }
}