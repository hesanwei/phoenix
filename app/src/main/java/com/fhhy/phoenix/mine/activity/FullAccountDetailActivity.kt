package com.fhhy.phoenix.mine.activity

import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.fhhy.phoenix.R
import com.fhhy.phoenix.base.BaseMvpActivity
import com.fhhy.phoenix.mine.activity.FundsAccountDetailActivity.Companion.FUNDS_NAME
import com.fhhy.phoenix.mine.adapter.FundsAccountDetailAdapter
import com.fhhy.phoenix.mine.contract.FullAccountDetailContract
import com.fhhy.phoenix.mine.presenter.FullAccountDetailPresenter
import com.jaeger.library.StatusBarUtil
import kotlinx.android.synthetic.main.activity_full_account_detail.*
import kotlinx.android.synthetic.main.layout_no_data.*

// Created by admin on 2020/6/20.
class FullAccountDetailActivity :
    BaseMvpActivity<FullAccountDetailContract.View, FullAccountDetailContract.Presenter>(),
    FullAccountDetailContract.View {

    private val fundsAccountDetailAdapter by lazy {
        FundsAccountDetailAdapter()
    }

    override fun createPresenter(): FullAccountDetailContract.Presenter =
        FullAccountDetailPresenter()

    override fun getLayoutId(): Int = R.layout.activity_full_account_detail

    override fun initView() {
        super.initView()
        StatusBarUtil.setTransparentForImageView(this, clTop)
        initData()
        initRecyclerView()
    }

    private fun initData() {
        val fundsName = intent.getStringExtra(FUNDS_NAME)
        tvTitle.text = fundsName
        tvUnrealizedPL.text = String.format(resources.getString(R.string.unrealized_P_L), fundsName)
        tvEquity.text = String.format(resources.getString(R.string.equity), fundsName)
        tvTransferIn.text = String.format(resources.getString(R.string.transfer_in_coin),fundsName)
        tvTransferOut.text = String.format(resources.getString(R.string.transfer_out_coin),fundsName)
    }

    private fun initRecyclerView() {
        recyclerView.visibility = View.VISIBLE
        dividerLine.visibility = View.VISIBLE
        clEmptyLayout.visibility = View.GONE
        recyclerView?.run {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@FullAccountDetailActivity)
            fundsAccountDetailAdapter.data = mutableListOf("", "")
            adapter = fundsAccountDetailAdapter
        }
        fundsAccountDetailAdapter.setOnItemClickListener { adapter, view, position ->
            startActivity(Intent(this, FundsRecordDetailActivity::class.java))
        }
    }
}