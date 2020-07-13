package com.fhhy.phoenix.home.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.fhhy.phoenix.R
import com.fhhy.phoenix.base.BaseMvpFragment
import com.fhhy.phoenix.bean.CurrencyPrice
import com.fhhy.phoenix.bean.HomeTopWrapBean
import com.fhhy.phoenix.contract.adapter.ContractAdapter
import com.fhhy.phoenix.contractdetail.ContractDetailActivity
import com.fhhy.phoenix.event.UpdateMsgUnReadNumEvent
import com.fhhy.phoenix.home.CurrencyPriceItemDiff
import com.fhhy.phoenix.home.HomeContract
import com.fhhy.phoenix.home.adapter.BannerAdapter
import com.fhhy.phoenix.home.adapter.NoticeMarqueeAdapter
import com.fhhy.phoenix.home.presenter.HomePresenter
import com.fhhy.phoenix.login.LoginActivity
import com.fhhy.phoenix.message.MessageCenterActivity
import com.jaeger.library.StatusBarUtil
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.fragment_home.*
import noDoubleClick
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

// Created by admin on 2020/6/7.
class HomeFragment : BaseMvpFragment<HomeContract.View, HomeContract.Presenter>(),
    HomeContract.View {

    private val homeContractAdapter by lazy {
        ContractAdapter()
    }

    private val homeBgWorker: HomeLifeCycle by lazy {
        HomeLifeCycle()
    }

    private val marqueeAdapter by lazy {
        NoticeMarqueeAdapter()
    }

    override fun createPresenter(): HomeContract.Presenter = HomePresenter()

    override fun getLayoutId(): Int = R.layout.fragment_home

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycle.addObserver(homeBgWorker)
    }

    override fun initView(view: View) {
        super.initView(view)
        StatusBarUtil.setTransparentForImageView(activity, clMessage)
        StatusBarUtil.setLightMode(activity)
        initMarqueeView()
        initRecyclerView()
        ensureSubscribe()
    }

    private fun ensureSubscribe() {
        addDisposable {
            homeBgWorker.currenciesList
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext {
                    Log.d("HomeFragment", "ensureSubscribe: update list")
                    updateCurrenciesList(it)
                }
                .subscribe()
        }
    }

    private fun updateCurrenciesList(list: List<CurrencyPrice>) {
        list.apply {
            homeContractAdapter.setDiffNewData(this.toMutableList())
        }
    }

    private fun initMarqueeView() {
        ivMessage.noDoubleClick {
            if (isLogin()) {
                startActivity(Intent(requireContext(), MessageCenterActivity::class.java))
            }else{
                startActivity(Intent(requireContext(), LoginActivity::class.java))
            }

        }
        xMarqueeContainer.isVisible = false
        homeNavContainer.isVisible = false
        xMarqueeView.setAdapter(marqueeAdapter)
    }

    private fun initRecyclerView() {
        recyclerView?.run {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = homeContractAdapter
        }
        homeContractAdapter.setOnItemClickListener { adapter, view, position ->
            startActivity(Intent(requireContext(), ContractDetailActivity::class.java))
        }
        homeContractAdapter.setDiffCallback(CurrencyPriceItemDiff())
        smartRefreshLayout.setOnRefreshListener {
            mPresenter?.requestHomeData()
        }
    }

    override fun lazyLoad() {
        mPresenter?.run {
            requestHomeData()
            if (isLogin()) {
                updateMsgUnReadNum()
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onReceiveUpdateMsgEvent(updateMsgUnReadNumEvent: UpdateMsgUnReadNumEvent) {
        mPresenter?.run {
            updateMsgUnReadNum()
        }
    }
    override fun useEventBus(): Boolean {
        return true
    }

    companion object {
        fun newInstance(): HomeFragment {
            return HomeFragment()
        }
    }

    override fun setHomeCurrencies(currencies: List<CurrencyPrice>) {
        smartRefreshLayout.finishRefresh()
        homeContractAdapter.setList(currencies)
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        homeBgWorker.onHideChanged(hidden)
    }

    override fun updateMsgUnReadNum(num: Int) {
        tvMessageCount.text = "$num"
    }
    override fun showBannerAndNav(topWrapBean: HomeTopWrapBean) {
        smartRefreshLayout.finishRefresh()
        //绑定banner
        xBanner.setBannerData(topWrapBean.bannerList)
        xBanner.loadImage(BannerAdapter(requireContext()))

        //绑定消息轮播
        marqueeAdapter.setData(topWrapBean.noticeList.map {
            it.title
        })

        xMarqueeContainer.isVisible = true
        homeNavContainer.isVisible = true

        //todo 设置四个按钮的点击事件
        tvNewGuide.setOnClickListener {

        }
        tvInviteFriends.setOnClickListener {

        }

        tvPlatformIntroduction.setOnClickListener {

        }

        tvRecharge.setOnClickListener {

        }
    }
}