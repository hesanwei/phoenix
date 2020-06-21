package com.fhhy.phoenix.mine.activity

import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.fhhy.phoenix.R
import com.fhhy.phoenix.base.BaseMvpActivity
import com.fhhy.phoenix.mine.activity.FundsAccountDetailActivity.Companion.FUNDS_NAME
import com.fhhy.phoenix.mine.adapter.CoinAddressAdapter
import com.fhhy.phoenix.mine.contract.CoinAddressContract
import com.fhhy.phoenix.mine.presenter.CoinAddressPresenter
import kotlinx.android.synthetic.main.activity_coin_address.*
import setViewClickListener

// Created by admin on 2020/6/21.
class CoinAddressActivity :
    BaseMvpActivity<CoinAddressContract.View, CoinAddressContract.Presenter>(),
    CoinAddressContract.View, View.OnClickListener {

    private var fundsName: String? = ""

    private val coinAddressAdapter by lazy {
        CoinAddressAdapter()
    }

    override fun createPresenter(): CoinAddressContract.Presenter = CoinAddressPresenter()

    override fun getLayoutId(): Int = R.layout.activity_coin_address

    override fun initView() {
        super.initView()
        fundsName = intent.getStringExtra(FUNDS_NAME)
        tvTitle.text = String.format(resources.getString(R.string.coin_withdraw_address), fundsName)
        initRecyclerView()
        setViewClickListener(this, btnBack, tvAddNewAddress)
    }

    private fun initRecyclerView() {
        recyclerView.run {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@CoinAddressActivity)
            coinAddressAdapter.data = mutableListOf("", "", "")
            adapter = coinAddressAdapter
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnBack -> finish()

            R.id.tvAddNewAddress -> {
                val intent = Intent(this, AddNewAddressActivity::class.java)
                intent.putExtra(FUNDS_NAME, fundsName)
                startActivity(intent)
            }
        }
    }
}