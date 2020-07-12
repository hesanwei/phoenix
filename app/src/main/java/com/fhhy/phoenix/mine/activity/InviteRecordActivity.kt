package com.fhhy.phoenix.mine.activity

import androidx.recyclerview.widget.LinearLayoutManager
import com.fhhy.phoenix.R
import com.fhhy.phoenix.base.BaseMvpActivity
import com.fhhy.phoenix.mine.adapter.InviteRecordAdapter
import com.fhhy.phoenix.mine.contract.InviteRecordContract
import com.fhhy.phoenix.mine.presenter.InviteRecordPresenter
import com.jaeger.library.StatusBarUtil
import kotlinx.android.synthetic.main.activity_invite_record.*

// Created by admin on 2020/7/12.
class InviteRecordActivity :
    BaseMvpActivity<InviteRecordContract.View, InviteRecordContract.Presenter>(),
    InviteRecordContract.View {

    private val inviteRecordAdapter by lazy {
        InviteRecordAdapter()
    }

    override fun createPresenter(): InviteRecordContract.Presenter = InviteRecordPresenter()

    override fun getLayoutId(): Int = R.layout.activity_invite_record

    override fun initView() {
        super.initView()
        StatusBarUtil.setTransparentForImageView(this, tvTitle)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        recyclerView?.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@InviteRecordActivity)
            inviteRecordAdapter.addData(mutableListOf("", "", "", ""))
            adapter = inviteRecordAdapter
        }
    }

}