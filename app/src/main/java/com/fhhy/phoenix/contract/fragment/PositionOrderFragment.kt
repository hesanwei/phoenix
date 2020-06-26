package com.fhhy.phoenix.contract.fragment

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.fhhy.phoenix.R
import com.fhhy.phoenix.base.BaseMvpFragment
import com.fhhy.phoenix.contract.OrderContract
import com.fhhy.phoenix.contract.adapter.PositionOrderAdapter
import com.fhhy.phoenix.contract.presenter.OrderPresenter
import kotlinx.android.synthetic.main.fragment_order.*

// Created by admin on 2020/6/26.
class PositionOrderFragment :
    BaseMvpFragment<OrderContract.View, OrderContract.Presenter>(),
    OrderContract.View {

    private val positionAdapter by lazy {
        PositionOrderAdapter()
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
            positionAdapter.data = mutableListOf("", "", "")
            adapter = positionAdapter
        }
    }

    override fun lazyLoad() {
    }


    companion object {
        fun newInstance(): PositionOrderFragment {
            return PositionOrderFragment()
        }
    }
}