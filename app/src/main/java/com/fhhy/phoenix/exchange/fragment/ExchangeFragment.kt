package com.fhhy.phoenix.exchange.fragment

import android.graphics.Color
import android.view.View
import com.fhhy.phoenix.R
import com.fhhy.phoenix.base.BaseMvpFragment
import com.fhhy.phoenix.exchange.ExchangeContract
import com.fhhy.phoenix.exchange.presenter.ExchangePresenter
import com.jaeger.library.StatusBarUtil
import kotlinx.android.synthetic.main.fragment_exchange.*

// Created by admin on 2020/6/7.
class ExchangeFragment : BaseMvpFragment<ExchangeContract.View, ExchangeContract.Presenter>() {
    override fun createPresenter(): ExchangeContract.Presenter = ExchangePresenter()

    override fun getLayoutId(): Int = R.layout.fragment_exchange

    override fun initView(view: View) {
        super.initView(view)
        StatusBarUtil.setTransparentForImageView(activity, tvTitle)
        StatusBarUtil.setLightMode(activity)
    }

    override fun lazyLoad() {
    }

    companion object {
        fun newInstance(): ExchangeFragment {
            return ExchangeFragment()
        }
    }
}