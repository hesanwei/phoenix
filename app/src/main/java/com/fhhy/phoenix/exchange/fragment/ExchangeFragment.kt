package com.fhhy.phoenix.exchange.fragment

import android.view.View
import com.fhhy.phoenix.R
import com.fhhy.phoenix.base.BaseMvpFragment
import com.fhhy.phoenix.dialog.CoinSelectDialog
import com.fhhy.phoenix.exchange.ExchangeContract
import com.fhhy.phoenix.exchange.presenter.ExchangePresenter
import com.jaeger.library.StatusBarUtil
import kotlinx.android.synthetic.main.fragment_exchange.*
import setViewClickListener

// Created by admin on 2020/6/7.
class ExchangeFragment : BaseMvpFragment<ExchangeContract.View, ExchangeContract.Presenter>(),
    View.OnClickListener {
    override fun createPresenter(): ExchangeContract.Presenter = ExchangePresenter()

    override fun getLayoutId(): Int = R.layout.fragment_exchange

    override fun initView(view: View) {
        super.initView(view)
        StatusBarUtil.setTransparentForImageView(activity, tvTitle)
        StatusBarUtil.setLightMode(activity)
        initViewClick()
    }

    private fun initViewClick() {
        setViewClickListener(
            this,
            tvCoinOne,
            tvCoinTwo,
            ivWarningMark,
            tvExchangeByCoin,
            tvAll,
            tvExchangeNow
        )
    }

    override fun lazyLoad() {
    }

    companion object {
        fun newInstance(): ExchangeFragment {
            return ExchangeFragment()
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tvCoinOne -> {//左边的币
                CoinSelectDialog(mutableListOf("BTC", "ETH", "USDT"),
                    object : CoinSelectDialog.OnItemSelectListener {
                        override fun onItemSelect(coin: String) {

                        }
                    }).show(activity!!.supportFragmentManager)
            }

            R.id.tvCoinTwo -> {//右边转换成的币
                CoinSelectDialog(mutableListOf("BTC", "ETH", "USDT"),
                    object : CoinSelectDialog.OnItemSelectListener {
                        override fun onItemSelect(coin: String) {

                        }
                    }).show(activity!!.supportFragmentManager)
            }

            R.id.ivWarningMark -> {//汇率的提示

            }

            R.id.tvExchangeByCoin -> {//按某币兑换

            }

            R.id.tvAll -> {//全部

            }

            R.id.tvExchangeNow -> {//立即兑换

            }
        }
    }
}