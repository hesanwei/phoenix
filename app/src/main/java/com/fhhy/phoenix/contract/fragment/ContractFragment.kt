package com.fhhy.phoenix.contract.fragment

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.fhhy.phoenix.R
import com.fhhy.phoenix.base.BaseMvpFragment
import com.fhhy.phoenix.contract.ContractContract
import com.fhhy.phoenix.contract.presenter.ContractPresenter
import com.fhhy.phoenix.mine.fragment.MineFragment
import com.fhhy.phoenix.test.ContractBean
import com.jaeger.library.StatusBarUtil
import kotlinx.android.synthetic.main.fragment_contract.*
import kotlinx.android.synthetic.main.fragment_contract.tvTitle
import kotlinx.android.synthetic.main.fragment_exchange.*
import setViewClickListener

// Created by admin on 2020/6/7.
class ContractFragment: BaseMvpFragment<ContractContract.View,ContractContract.Presenter>() {

    private val adapter: ContractAdapter by lazy {
        ContractAdapter()
    }

    override fun createPresenter(): ContractContract.Presenter = ContractPresenter()

    override fun getLayoutId(): Int = R.layout.fragment_contract

    override fun initView(view: View) {
        super.initView(view)
        StatusBarUtil.setTransparentForImageView(activity, tvTitle)
        StatusBarUtil.setLightMode(activity)
        rvContractList.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = this@ContractFragment.adapter
        }

    }

    override fun lazyLoad() {
        val testData = arrayListOf<ContractBean>()
        for (i in 1..10) {
            testData.add(ContractBean("ETH/USDT", 9987.5f/i, 0.00f))
        }
        adapter.data.addAll(testData)
    }

    companion object {
        fun newInstance(): ContractFragment {
            return ContractFragment()
        }
    }
}