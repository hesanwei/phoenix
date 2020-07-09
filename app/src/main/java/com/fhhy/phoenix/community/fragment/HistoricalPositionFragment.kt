package com.fhhy.phoenix.community.fragment

import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.fhhy.phoenix.R
import com.fhhy.phoenix.base.BaseMvpFragment
import com.fhhy.phoenix.community.activity.TraderInfoActivity
import com.fhhy.phoenix.community.adapter.HistoricalPositionAdapter
import com.fhhy.phoenix.community.contract.HistoricalPositionContract
import com.fhhy.phoenix.community.presenter.HistoricalPositionPresenter
import com.fhhy.phoenix.contract.activity.HistoryOrderDetailActivity
import com.fhhy.phoenix.contract.adapter.HistoryOrderAdapter
import com.fhhy.phoenix.contract.fragment.OrderFragment
import kotlinx.android.synthetic.main.activity_login.view.*
import kotlinx.android.synthetic.main.fragment_order.*

/**
 * Created by heCunCun on 2020/7/9
 */
class HistoricalPositionFragment :BaseMvpFragment<HistoricalPositionContract.View,HistoricalPositionContract.Presenter>(),HistoricalPositionContract.View {
    private val historicalPositionAdapter by lazy {
        HistoricalPositionAdapter()
    }

    override fun createPresenter(): HistoricalPositionContract.Presenter=HistoricalPositionPresenter()

    override fun getLayoutId(): Int = R.layout.fragment_historical_position

    override fun lazyLoad() {

    }

    override fun initView(view: View) {
        super.initView(view)
        initRecyclerView()
    }
    private fun initRecyclerView() {
        recyclerView?.run {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = historicalPositionAdapter
            historicalPositionAdapter.data = mutableListOf("", "", "","","","","","")
        }
        historicalPositionAdapter.setOnItemClickListener { adapter, view, position ->
           // startActivity(Intent(requireContext(), TraderInfoActivity::class.java))
        }
    }
    companion object{
        fun newInstance():HistoricalPositionFragment{
            return HistoricalPositionFragment()
        }
    }
}