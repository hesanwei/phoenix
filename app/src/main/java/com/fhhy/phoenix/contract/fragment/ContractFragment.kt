package com.fhhy.phoenix.contract.fragment

import android.view.View
import com.fhhy.phoenix.R
import com.fhhy.phoenix.base.BaseMvpFragment
import com.fhhy.phoenix.contract.ContractContract
import com.fhhy.phoenix.contract.presenter.ContractPresenter
import com.fhhy.phoenix.mine.fragment.MineFragment

// Created by admin on 2020/6/7.
class ContractFragment: BaseMvpFragment<ContractContract.View,ContractContract.Presenter>() {
    override fun createPresenter(): ContractContract.Presenter = ContractPresenter()

    override fun getLayoutId(): Int = R.layout.fragment_contract

    override fun initView(view: View) {
        super.initView(view)

    }

    override fun lazyLoad() {
    }

    companion object {
        fun newInstance(): ContractFragment {
            return ContractFragment()
        }
    }
}