package com.fhhy.phoenix.mine.fragment

import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.fhhy.phoenix.R
import com.fhhy.phoenix.base.BaseMvpFragment
import com.fhhy.phoenix.mine.activity.FundsAccountDetailActivity
import com.fhhy.phoenix.mine.adapter.AccountFundsAdapter
import com.fhhy.phoenix.mine.contract.AccountFundsContract
import com.fhhy.phoenix.mine.presenter.AccountFundsPresenter
import kotlinx.android.synthetic.main.fragment_account_funds.*

// Created by admin on 2020/6/14.
class AccountFundsFragment :
    BaseMvpFragment<AccountFundsContract.View, AccountFundsContract.Presenter>(),
    AccountFundsContract.View {

    private val accountFundsAdapter by lazy {
        AccountFundsAdapter()
    }

    override fun createPresenter(): AccountFundsContract.Presenter = AccountFundsPresenter()

    override fun getLayoutId(): Int = R.layout.fragment_account_funds

    override fun initView(view: View) {
        super.initView(view)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        recyclerView?.run {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            accountFundsAdapter.data = mutableListOf("BTC", "ETH", "USDT", "VST")
            adapter = accountFundsAdapter
        }

        accountFundsAdapter.setOnItemClickListener { adapter, view, position ->
            val intent = Intent(context, FundsAccountDetailActivity::class.java)
            val item = accountFundsAdapter.data[position]
            intent.putExtra(FundsAccountDetailActivity.FUNDS_NAME, item)
            startActivity(intent)
        }
    }

    override fun lazyLoad() {
    }

    companion object {
        fun newInstance(): AccountFundsFragment {
            return AccountFundsFragment()
        }
    }
}