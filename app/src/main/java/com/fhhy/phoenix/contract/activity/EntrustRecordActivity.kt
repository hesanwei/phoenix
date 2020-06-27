package com.fhhy.phoenix.contract.activity

import androidx.fragment.app.Fragment
import com.fhhy.phoenix.R
import com.fhhy.phoenix.base.BaseActivity
import com.fhhy.phoenix.contract.fragment.EntrustRecordFragment
import kotlinx.android.synthetic.main.activity_entrust_record.*

// Created by admin on 2020/6/27.
class EntrustRecordActivity : BaseActivity() {

    private val inEntrustFragment by lazy {
        EntrustRecordFragment.newInstance(EntrustRecordFragment.STATE_IN_ENTRUST)
    }

    private val historyFragment by lazy {
        EntrustRecordFragment.newInstance(EntrustRecordFragment.STATE_HISTORY)
    }

    override fun getLayoutId(): Int = R.layout.activity_entrust_record

    override fun initView() {
        initRadioGroup()
        btnBack.setOnClickListener {
            finish()
        }
    }

    private fun initRadioGroup() {
        replaceFragment(inEntrustFragment)
        radioGroup.check(R.id.rbInEntrust)
        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.rbInEntrust -> replaceFragment(inEntrustFragment)
                R.id.rbHistory -> replaceFragment(historyFragment)
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val beginTransaction = supportFragmentManager.beginTransaction()
        beginTransaction.replace(R.id.flContainer, fragment)
        beginTransaction.commit()
    }

}