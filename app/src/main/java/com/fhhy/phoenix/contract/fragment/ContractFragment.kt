package com.fhhy.phoenix.contract.fragment

import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.fhhy.phoenix.R
import com.fhhy.phoenix.base.BaseMvpFragment
import com.fhhy.phoenix.bean.CurrencyPrice
import com.fhhy.phoenix.contract.adapter.ContractAdapter
import com.fhhy.phoenix.contract.contract.ContractContract
import com.fhhy.phoenix.contract.presenter.ContractPresenter
import com.fhhy.phoenix.contractdetail.ContractDetailActivity
import com.fhhy.phoenix.test.ContractBean
import com.jaeger.library.StatusBarUtil
import kotlinx.android.synthetic.main.fragment_contract.*
import kotlin.random.Random


// Created by admin on 2020/6/7.
class ContractFragment: BaseMvpFragment<ContractContract.View, ContractContract.Presenter>() {

    private val adapter: ContractAdapter by lazy {
        ContractAdapter()
    }

    override fun createPresenter(): ContractContract.Presenter = ContractPresenter()

    override fun getLayoutId(): Int = R.layout.fragment_contract

    override fun initView(view: View) {
        super.initView(view)
        StatusBarUtil.setTransparentForImageView(activity, tvTitle)
        StatusBarUtil.setLightMode(activity)
        setupRecyclerView()
        setupRefreshView()
    }

    private fun setupRefreshView() {
        refreshLayout.setOnRefreshListener {
            refreshLayout.finishRefresh(1500)
        }
    }

    private fun setupRecyclerView() {
        rvContractList.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = this@ContractFragment.adapter
        }
        // 设置点击事件
        adapter.setOnItemClickListener { adapter, view, position ->
            startActivity(Intent(requireContext(), ContractDetailActivity::class.java))
        }

    }

    override fun lazyLoad() {
        val testData = arrayListOf<CurrencyPrice>()
        for (i in 1..10) {
            val up = Random.nextBoolean()
            testData.add(CurrencyPrice("${9987.5f/i}", "+1.50", "ETH", if (up) "up" else "down"))
        }
        adapter.data.addAll(testData)
    }

    companion object {
        fun newInstance(): ContractFragment {
            return ContractFragment()
        }
    }
}