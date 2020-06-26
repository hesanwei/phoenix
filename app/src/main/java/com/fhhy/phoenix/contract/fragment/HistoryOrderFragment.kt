package com.fhhy.phoenix.contract.fragment

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.fhhy.phoenix.R
import com.fhhy.phoenix.base.BaseMvpFragment
import com.fhhy.phoenix.contract.OrderContract
import com.fhhy.phoenix.contract.adapter.HistoryOrderAdapter
import com.fhhy.phoenix.contract.presenter.OrderPresenter
import kotlinx.android.synthetic.main.fragment_order.*

// Created by admin on 2020/6/26.
class HistoryOrderFragment : BaseMvpFragment<OrderContract.View, OrderContract.Presenter>(),
    OrderContract.View {

    private val historyAdapter by lazy {
        HistoryOrderAdapter()
    }

    override fun createPresenter(): OrderContract.Presenter = OrderPresenter()

    override fun getLayoutId(): Int = R.layout.fragment_order

    override fun initView(view: View) {
        super.initView(view)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        recyclerView?.run {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            historyAdapter.data = mutableListOf("", "", "")
            adapter = historyAdapter
        }
    }

    override fun lazyLoad() {
    }

    companion object {
        fun newInstance(): HistoryOrderFragment {
            return HistoryOrderFragment()
        }
    }
}