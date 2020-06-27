package com.fhhy.phoenix.contract.fragment

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.fhhy.phoenix.R
import com.fhhy.phoenix.base.BaseMvpFragment
import com.fhhy.phoenix.contract.contract.OrderContract
import com.fhhy.phoenix.contract.activity.HistoryOrderDetailActivity
import com.fhhy.phoenix.contract.adapter.HistoryOrderAdapter
import com.fhhy.phoenix.contract.adapter.PositionOrderAdapter
import com.fhhy.phoenix.contract.presenter.OrderPresenter
import kotlinx.android.synthetic.main.fragment_order.*

// Created by admin on 2020/6/26.
class OrderFragment : BaseMvpFragment<OrderContract.View, OrderContract.Presenter>(),
    OrderContract.View {

    private var orderState: Int? = STATE_POSITION

    private val historyAdapter by lazy {
        HistoryOrderAdapter()
    }

    private val positionAdapter by lazy {
        PositionOrderAdapter()
    }

    override fun createPresenter(): OrderContract.Presenter = OrderPresenter()

    override fun getLayoutId(): Int = R.layout.fragment_order

    override fun initView(view: View) {
        super.initView(view)
        orderState = arguments?.getInt(ORDER_STATE)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        recyclerView?.run {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            if (orderState == STATE_POSITION) {
                positionAdapter.data = mutableListOf("", "", "")
                adapter = positionAdapter
            } else {
                historyAdapter.data = mutableListOf("", "", "")
                adapter = historyAdapter
            }

        }
        historyAdapter.setOnItemClickListener { adapter, view, position ->
            startActivity(Intent(context, HistoryOrderDetailActivity::class.java))
        }
    }

    override fun lazyLoad() {
    }

    companion object {
        const val STATE_POSITION = 0
        const val STATE_HISTORY = 1
        private const val ORDER_STATE = "order_state"
        fun newInstance(state: Int): OrderFragment {
            val orderFragment = OrderFragment()
            val bundle = Bundle()
            bundle.putInt(ORDER_STATE, state)
            orderFragment.arguments = bundle
            return orderFragment
        }
    }
}