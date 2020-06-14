package com.fhhy.phoenix.mine.activity

import androidx.fragment.app.Fragment
import com.fhhy.phoenix.R
import com.fhhy.phoenix.base.BaseActivity
import com.fhhy.phoenix.mine.adapter.AccountPagerAdapter
import com.fhhy.phoenix.mine.fragment.AccountFullFragment
import com.fhhy.phoenix.mine.fragment.AccountFundsFragment
import kotlinx.android.synthetic.main.activity_account.*

// Created by admin on 2020/6/14.
class AccountActivity : BaseActivity() {

    private val titleList by lazy {
        mutableListOf(
            resources.getString(R.string.funds),
            resources.getString(R.string.standard_full)
        )
    }

    private val fragmentList by lazy {
        mutableListOf<Fragment>(AccountFundsFragment.newInstance(), AccountFullFragment.newInstance())
    }

    private val pagerAdapter by lazy {
        AccountPagerAdapter(supportFragmentManager, titleList, fragmentList)
    }

    override fun getLayoutId(): Int = R.layout.activity_account

    override fun initView() {
        val accountType = intent.getStringExtra(ACCOUNT_TYPE)
        initViewPager(accountType)
        btnBack.setOnClickListener {
            finish()
        }
    }

    private fun initViewPager(accountType: String?) {
        viewPager.adapter = pagerAdapter
        tabLayout.setupWithViewPager(viewPager)
        if (accountType == ACCOUNT_TYPE_FULL) {
            viewPager.setCurrentItem(1, true)
        }
    }

    companion object {
        const val ACCOUNT_TYPE = "account_type"//账户类型
        const val ACCOUNT_TYPE_FUNDS = "account_type_funds"//账户类型--资金
        const val ACCOUNT_TYPE_FULL = "account_type_full"//账户类型--全仓
    }
}