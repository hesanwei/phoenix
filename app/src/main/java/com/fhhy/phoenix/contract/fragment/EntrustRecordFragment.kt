package com.fhhy.phoenix.contract.fragment

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.fhhy.phoenix.R
import com.fhhy.phoenix.base.BaseMvpFragment
import com.fhhy.phoenix.contract.activity.HistoryOrderDetailActivity
import com.fhhy.phoenix.contract.adapter.EntrustRecordAdapter
import com.fhhy.phoenix.contract.adapter.HistoryEntrustRecordAdapter
import com.fhhy.phoenix.contract.contract.EntrustRecordContract
import com.fhhy.phoenix.contract.presenter.EntrustRecordPresenter
import kotlinx.android.synthetic.main.fragment_entrust_record.*

// Created by admin on 2020/6/27.
class EntrustRecordFragment :
    BaseMvpFragment<EntrustRecordContract.View, EntrustRecordContract.Presenter>(),
    EntrustRecordContract.View {

    private var entrustRecordState: Int? = STATE_IN_ENTRUST

    private val entrustAdapter by lazy {
        EntrustRecordAdapter()
    }

    private val historyAdapter by lazy {
        HistoryEntrustRecordAdapter()
    }

    override fun createPresenter(): EntrustRecordContract.Presenter = EntrustRecordPresenter()

    override fun getLayoutId(): Int = R.layout.fragment_entrust_record

    override fun initView(view: View) {
        super.initView(view)
        entrustRecordState = arguments?.getInt(ENTRUST_RECORD_STATE)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        recyclerView?.run {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            if (entrustRecordState == STATE_IN_ENTRUST) {
                entrustAdapter.data = mutableListOf("", "", "")
                adapter = entrustAdapter
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
        const val STATE_IN_ENTRUST = 0
        const val STATE_HISTORY = 1
        private const val ENTRUST_RECORD_STATE = "entrust_record_state"
        fun newInstance(state: Int): EntrustRecordFragment {
            val entrustRecordFragment = EntrustRecordFragment()
            val bundle = Bundle()
            bundle.putInt(ENTRUST_RECORD_STATE, state)
            entrustRecordFragment.arguments = bundle
            return entrustRecordFragment
        }
    }
}