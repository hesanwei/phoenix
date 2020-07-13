package com.fhhy.phoenix.mine.activity

import android.app.Activity
import android.content.Intent
import androidx.recyclerview.widget.LinearLayoutManager
import com.fhhy.phoenix.R
import com.fhhy.phoenix.base.BaseMvpActivity
import com.fhhy.phoenix.bean.CountryListBean
import com.fhhy.phoenix.constants.SPKeyConstants
import com.fhhy.phoenix.mine.adapter.CountryListAdapter
import com.fhhy.phoenix.mine.contract.CountryContract
import com.fhhy.phoenix.mine.presenter.CountryPresenter
import kotlinx.android.synthetic.main.activity_country_list.*

// Created by admin on 2020/7/12.
class CountryListActivity : BaseMvpActivity<CountryContract.View, CountryContract.Presenter>(),
    CountryContract.View {

    private val countryListAdapter by lazy {
        CountryListAdapter()
    }

    override fun createPresenter(): CountryContract.Presenter = CountryPresenter()

    override fun getLayoutId(): Int = R.layout.activity_country_list

    override fun initView() {
        super.initView()
        initRecyclerView()
        mPresenter?.requestCountryList()
    }

    private fun initRecyclerView() {
        recyclerView?.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@CountryListActivity)
            adapter = countryListAdapter
        }

        countryListAdapter.setOnItemClickListener { adapter, view, position ->
            val countryListBean = countryListAdapter.data[position]
            val intent = Intent()
            intent.putExtra(SPKeyConstants.SP_KEY_COUNTRY, countryListBean)
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }

    override fun requestCountryListSuccess(dataList: List<CountryListBean>?) {
        countryListAdapter.setList(dataList)
    }
}