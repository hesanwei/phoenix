package com.fhhy.phoenix.mine.activity

import androidx.recyclerview.widget.LinearLayoutManager
import com.fhhy.phoenix.R
import com.fhhy.phoenix.base.BaseActivity
import com.fhhy.phoenix.mine.adapter.BonusAdapter
import kotlinx.android.synthetic.main.activity_my_bonus.*

// Created by admin on 2020/6/21.
class MyBonusActivity : BaseActivity() {

    private val bonusAdapter by lazy {
        BonusAdapter()
    }

    override fun getLayoutId(): Int = R.layout.activity_my_bonus

    override fun initView() {
        radioGroup.check(R.id.rbBeUse)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        recyclerView?.run {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MyBonusActivity)
            bonusAdapter.data = mutableListOf("", "", "", "", "")
            adapter = bonusAdapter
        }
    }
}