package com.fhhy.phoenix.home.fragment

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.fhhy.phoenix.R
import com.fhhy.phoenix.base.BaseMvpFragment
import com.fhhy.phoenix.contract.fragment.ContractAdapter
import com.fhhy.phoenix.home.HomeContract
import com.fhhy.phoenix.home.adapter.BannerAdapter
import com.fhhy.phoenix.home.adapter.NoticeMarqueeAdapter
import com.fhhy.phoenix.home.presenter.HomePresenter
import com.fhhy.phoenix.test.BannerBean
import com.fhhy.phoenix.test.ContractBean
import com.jaeger.library.StatusBarUtil
import com.stx.xhb.xbanner.XBanner
import kotlinx.android.synthetic.main.fragment_home.*

// Created by admin on 2020/6/7.
class HomeFragment : BaseMvpFragment<HomeContract.View, HomeContract.Presenter>() {

    private val homeContractAdapter by lazy {
        ContractAdapter()
    }

    override fun createPresenter(): HomeContract.Presenter = HomePresenter()

    override fun getLayoutId(): Int = R.layout.fragment_home

    override fun initView(view: View) {
        super.initView(view)
        StatusBarUtil.setTransparentForImageView(activity, clMessage)
        StatusBarUtil.setLightMode(activity)
        initMarqueeView()
        initRecyclerView()
        initBannerView()
    }

    private fun initMarqueeView() {
        xMarqueeView.setAdapter(
            NoticeMarqueeAdapter(
                listOf(
                    "重要消息传达不到位？试试公告",
                    "重要消息传达不到位？试试公告",
                    "重要消息传达不到位？试试公告",
                    "重要消息传达不到位？试试公告"
                )
            )
        )
    }

    private fun initRecyclerView() {
        recyclerView?.run {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = homeContractAdapter
        }
    }

    private fun initBannerView() {
        xBanner.setBannerData(mutableListOf(BannerBean(), BannerBean(), BannerBean(), BannerBean()))
        xBanner.loadImage(BannerAdapter(context!!))
    }

    override fun lazyLoad() {
        val testData = arrayListOf<ContractBean>()
        for (i in 1..10) {
            testData.add(ContractBean("ETH/USDT", 9987.5f / i, 0.00f))
        }
        homeContractAdapter.data.addAll(testData)
    }

    companion object {
        fun newInstance(): HomeFragment {
            return HomeFragment()
        }
    }
}