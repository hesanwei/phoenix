package com.fhhy.phoenix.contract.activity

import android.view.View
import androidx.fragment.app.Fragment
import com.fhhy.phoenix.R
import com.fhhy.phoenix.base.BaseActivity
import com.fhhy.phoenix.contract.fragment.HistoryOrderFragment
import com.fhhy.phoenix.contract.fragment.PositionOrderFragment
import kotlinx.android.synthetic.main.activity_order.*
import setViewClickListener

// Created by admin on 2020/6/26.
class OrderActivity : BaseActivity(), View.OnClickListener {

    private val positionFragment by lazy {
        PositionOrderFragment.newInstance()
    }

    private val historyFragment by lazy {
        HistoryOrderFragment.newInstance()
    }

    override fun getLayoutId(): Int = R.layout.activity_order

    override fun initView() {
        initRadioGroup()
        setViewClickListener(this, btnBack, tvEntrustRecord)
    }

    private fun initRadioGroup() {
        replaceFragment(positionFragment)
        radioGroup.check(R.id.rbInPosition)
        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.rbInPosition -> replaceFragment(positionFragment)
                R.id.rbHistory -> replaceFragment(historyFragment)
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val beginTransaction = supportFragmentManager.beginTransaction()
        beginTransaction.replace(R.id.flContainer, fragment)
        beginTransaction.commit()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnBack -> finish()
            R.id.tvEntrustRecord -> {

            }
        }
    }

}