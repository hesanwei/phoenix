package com.fhhy.phoenix.mine.fragment

import android.view.View
import com.fhhy.phoenix.R
import com.fhhy.phoenix.base.BaseMvpFragment
import com.fhhy.phoenix.mine.MineContract
import com.fhhy.phoenix.mine.presenter.MinePresenter

// Created by admin on 2020/6/7.
class MineFragment : BaseMvpFragment<MineContract.View, MineContract.Presenter>() {
    override fun createPresenter(): MineContract.Presenter = MinePresenter()

    override fun getLayoutId(): Int = R.layout.fragment_mine

    override fun initView(view: View) {
        super.initView(view)

    }

    override fun lazyLoad() {
    }

    companion object {
        fun newInstance(): MineFragment {
            return MineFragment()
        }
    }
}