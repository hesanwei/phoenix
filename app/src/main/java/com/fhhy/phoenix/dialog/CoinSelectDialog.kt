package com.fhhy.phoenix.dialog

import androidx.recyclerview.widget.LinearLayoutManager
import com.fhhy.phoenix.R
import com.fhhy.phoenix.exchange.adapter.CoinSelectAdapter
import kotlinx.android.synthetic.main.dialog_coin_select.*

// Created by admin on 2020/6/13.
class CoinSelectDialog(
    private val dataList: MutableList<String>,
    private val listener: OnItemSelectListener
) : BaseDialog() {

    private val coinSelectAdapter by lazy {
        CoinSelectAdapter()
    }

    override fun getLayoutId(): Int = R.layout.dialog_coin_select

    override fun initView() {
        initRecyclerView()
    }

    private fun initRecyclerView() {
        recyclerView?.run {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            coinSelectAdapter.data = dataList
            adapter = coinSelectAdapter
        }
        coinSelectAdapter.setOnItemClickListener { adapter, view, position ->
            listener.onItemSelect(coinSelectAdapter.data[position])
            dismiss()
        }
    }

    interface OnItemSelectListener {
        fun onItemSelect(coin: String)
    }
}