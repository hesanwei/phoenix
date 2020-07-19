package com.fhhy.phoenix.mine.activity

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.fhhy.phoenix.R
import com.fhhy.phoenix.base.BaseActivity
import com.fhhy.phoenix.base.BaseMvpActivity
import com.fhhy.phoenix.bean.BonusBean
import com.fhhy.phoenix.mine.adapter.BonusAdapter
import com.fhhy.phoenix.mine.contract.MyBonusContract
import com.fhhy.phoenix.mine.presenter.MyBonusPresenter
import com.fhhy.phoenix.mine.presenter.MyLevelPresenter
import kotlinx.android.synthetic.main.activity_my_bonus.*

// Created by admin on 2020/6/21.

class MyBonusActivity : BaseMvpActivity<MyBonusContract.View, MyBonusContract.Presenter>(),
    MyBonusContract.View {

    private val bonusAdapter by lazy {
        BonusAdapter()
    }

    private var type = TYPE_NO_USE

    override fun createPresenter(): MyBonusContract.Presenter = MyBonusPresenter()

    override fun getLayoutId(): Int = R.layout.activity_my_bonus

    override fun initView() {
        super.initView()
        initRecyclerView()
        initRadioGroup()
        mPresenter?.requestBonusList(type)
    }

    private fun initRadioGroup() {
        radioGroup.check(R.id.rbBeUse)
        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.rbBeUse -> {
                    type = TYPE_NO_USE
                    mPresenter?.requestBonusList(type)
                }

                R.id.rbHistory -> {
                    type = TYPE_USED
                    mPresenter?.requestBonusList(type)
                }
            }
        }
    }

    private fun initRecyclerView() {
        recyclerView?.run {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MyBonusActivity)
            adapter = bonusAdapter
        }
    }

    override fun requestBonusListSuccess(dataList: List<BonusBean>?) {
        bonusAdapter.setList(dataList)
        if (dataList != null && dataList.isNotEmpty()) {
            recyclerView.visibility = View.VISIBLE
            clEmptyLayout.visibility = View.GONE
        } else {
            recyclerView.visibility = View.GONE
            clEmptyLayout.visibility = View.VISIBLE
        }
    }

    companion object {
        const val TYPE_USED = "1" //已使用
        const val TYPE_NO_USE = "2" //未使用
        const val TYPE_ALL = "3" //全部
    }
}