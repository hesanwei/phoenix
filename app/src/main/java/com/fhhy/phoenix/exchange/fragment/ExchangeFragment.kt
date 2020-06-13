package com.fhhy.phoenix.exchange.fragment

import android.view.View
import android.widget.Toast
import com.fhhy.phoenix.R
import com.fhhy.phoenix.base.BaseMvpFragment
import com.fhhy.phoenix.exchange.ExchangeContract
import com.fhhy.phoenix.exchange.presenter.ExchangePresenter
import com.jaeger.library.StatusBarUtil
import com.jakewharton.rxbinding4.view.clicks
import com.jakewharton.rxbinding4.widget.textChanges
import kotlinx.android.synthetic.main.fragment_exchange.*
import setViewClickListener

// Created by admin on 2020/6/7.
class ExchangeFragment : BaseMvpFragment<ExchangeContract.View, ExchangeContract.Presenter>() {
    override fun createPresenter(): ExchangeContract.Presenter = ExchangePresenter()

    override fun getLayoutId(): Int = R.layout.fragment_exchange

    override fun initView(view: View) {
        super.initView(view)
        StatusBarUtil.setTransparentForImageView(activity, tvTitle)
        StatusBarUtil.setLightMode(activity)
        initViewClick()
    }

    private fun initViewClick(){
        val subscribe = etExchangeCoin.textChanges()
            .subscribe {
                Toast.makeText(requireContext(), "文字变化了", Toast.LENGTH_SHORT).show()
            }

        tvExchangeNow.clicks()
            .doOnNext {
                Toast.makeText(requireContext(), "按钮被点击了", Toast.LENGTH_SHORT).show()
            }
            .subscribe()

    }

    override fun lazyLoad() {
    }

    companion object {
        fun newInstance(): ExchangeFragment {
            return ExchangeFragment()
        }
    }
}