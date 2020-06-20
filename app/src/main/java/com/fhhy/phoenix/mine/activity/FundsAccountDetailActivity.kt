package com.fhhy.phoenix.mine.activity

import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.fhhy.phoenix.R
import com.fhhy.phoenix.base.BaseMvpActivity
import com.fhhy.phoenix.mine.adapter.FundsAccountDetailAdapter
import com.fhhy.phoenix.mine.contract.FundsAccountDetailContract
import com.fhhy.phoenix.mine.presenter.FundsAccountDetailPresenter
import com.jaeger.library.StatusBarUtil
import kotlinx.android.synthetic.main.activity_funds_account_detail.*
import kotlinx.android.synthetic.main.layout_no_data.*
import setViewClickListener
import showToast

// Created by admin on 2020/6/14.
class FundsAccountDetailActivity :
    BaseMvpActivity<FundsAccountDetailContract.View, FundsAccountDetailContract.Presenter>(),
    FundsAccountDetailContract.View, View.OnClickListener {

    private var title: String? = ""

    private val fundsAccountDetailAdapter by lazy {
        FundsAccountDetailAdapter()
    }

    override fun createPresenter(): FundsAccountDetailContract.Presenter =
        FundsAccountDetailPresenter()

    override fun getLayoutId(): Int = R.layout.activity_funds_account_detail

    override fun initView() {
        super.initView()
        StatusBarUtil.setTransparentForImageView(this, clTop)
        title = intent.getStringExtra(FUNDS_NAME)
        tvTitle.text = title
        initRecyclerView()
        setViewClickListener(this, btnBack, tvWithdraw, tvRecharge)
    }

    private fun initRecyclerView() {
        recyclerView.visibility = View.VISIBLE
        dividerLine.visibility = View.VISIBLE
        clEmptyLayout.visibility = View.GONE
        recyclerView?.run {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@FundsAccountDetailActivity)
            fundsAccountDetailAdapter.data = mutableListOf("", "")
            adapter = fundsAccountDetailAdapter
        }
        fundsAccountDetailAdapter.setOnItemClickListener { adapter, view, position ->
            startActivity(Intent(this, FundsRecordDetailActivity::class.java))
        }
    }

    companion object {
        const val FUNDS_NAME = "funds_name"
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnBack -> finish()

            R.id.tvRecharge -> {
                val intent = Intent(this, RechargeActivity::class.java)
                intent.putExtra(FUNDS_NAME,title)
                startActivity(intent)
            }

            R.id.tvWithdraw -> {
                val intent = Intent(this, WithdrawActivity::class.java)
                intent.putExtra(FUNDS_NAME,title)
                startActivity(intent)
            }
        }
    }
}