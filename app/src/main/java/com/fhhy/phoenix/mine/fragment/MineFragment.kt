package com.fhhy.phoenix.mine.fragment

import android.view.View
import com.fhhy.phoenix.R
import com.fhhy.phoenix.base.BaseMvpFragment
import com.fhhy.phoenix.mine.MineContract
import com.fhhy.phoenix.mine.presenter.MinePresenter
import com.jaeger.library.StatusBarUtil
import kotlinx.android.synthetic.main.fragment_mine.*

// Created by admin on 2020/6/7.
class MineFragment : BaseMvpFragment<MineContract.View, MineContract.Presenter>() {
    //测试
    override fun createPresenter(): MineContract.Presenter = MinePresenter()

    override fun getLayoutId(): Int = R.layout.fragment_mine

    override fun initView(view: View) {
        super.initView(view)
        StatusBarUtil.setTransparentForImageView(activity, smartRefreshLayout)
        StatusBarUtil.setLightMode(activity)
    }

    override fun lazyLoad() {
    }

    companion object {
        fun newInstance(): MineFragment {
            return MineFragment()
        }
    }
}