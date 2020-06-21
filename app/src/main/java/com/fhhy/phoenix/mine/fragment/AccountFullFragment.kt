package com.fhhy.phoenix.mine.fragment

import android.content.Intent
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.fhhy.phoenix.R
import com.fhhy.phoenix.base.BaseMvpFragment
import com.fhhy.phoenix.mine.activity.ContractModeSettingActivity
import com.fhhy.phoenix.mine.activity.FullAccountDetailActivity
import com.fhhy.phoenix.mine.activity.FundsAccountDetailActivity.Companion.FUNDS_NAME
import com.fhhy.phoenix.mine.adapter.AccountFullAdapter
import com.fhhy.phoenix.mine.contract.AccountFullContract
import com.fhhy.phoenix.mine.presenter.AccountFullPresenter
import com.fhhy.phoenix.mine.widgets.BlueSpanText
import kotlinx.android.synthetic.main.fragment_account_full.*
import showToast

// Created by admin on 2020/6/14.
class AccountFullFragment :
    BaseMvpFragment<AccountFullContract.View, AccountFullContract.Presenter>(),
    AccountFullContract.View {

    private var isFull = false//是否为全仓 false则是标准全仓

    private val accountFullAdapter by lazy {
        AccountFullAdapter()
    }

    override fun createPresenter(): AccountFullContract.Presenter = AccountFullPresenter()

    override fun getLayoutId(): Int = R.layout.fragment_account_full

    override fun initView(view: View) {
        super.initView(view)
        initRecyclerView()
        initTips()
    }

    private fun initTips() {
        if (isFull) {
            tvAccountFullTip.text = resources.getString(R.string.account_full_tip_two)
        } else {
            val spannableString =
                SpannableString(resources.getString(R.string.account_full_tip_one))
            spannableString.setSpan(object : BlueSpanText(context!!) {
                override fun onClick(widget: View) {
                    startActivity(Intent(context, ContractModeSettingActivity::class.java))
                }
            }, spannableString.length - 4, spannableString.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            tvAccountFullTip.text = spannableString;
            tvAccountFullTip.movementMethod = LinkMovementMethod.getInstance();//不设置 没有点击事件
        }
    }

    private fun initRecyclerView() {
        recyclerView?.run {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            accountFullAdapter.data = mutableListOf("BTC", "ETH", "USDT", "VST")
            adapter = accountFullAdapter
        }

        accountFullAdapter.setOnItemClickListener { adapter, view, position ->
            val intent = Intent(context, FullAccountDetailActivity::class.java)
            intent.putExtra(FUNDS_NAME,accountFullAdapter.data[position])
            startActivity(intent)
        }
    }

    override fun lazyLoad() {
    }

    companion object {
        fun newInstance(): AccountFullFragment {
            return AccountFullFragment()
        }
    }
}