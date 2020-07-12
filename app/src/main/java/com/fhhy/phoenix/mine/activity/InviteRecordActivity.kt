package com.fhhy.phoenix.mine.activity

import androidx.recyclerview.widget.LinearLayoutManager
import com.fhhy.phoenix.R
import com.fhhy.phoenix.base.BaseMvpActivity
import com.fhhy.phoenix.bean.InviteRecordBean
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
        mPresenter?.requestInviteRecord(1)
        btnBack.setOnClickListener { finish() }
    }

    private fun initRecyclerView() {
        recyclerView?.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@InviteRecordActivity)
            adapter = inviteRecordAdapter
        }
    }

    override fun requestInviteRecordSuccess(inviteRecordBean: InviteRecordBean?) {
        inviteRecordBean?.apply {
            today?.apply {
                tvTodayTransactionsNumber.text = transactions_num
                tvTodayTurnover.text = turnover
                tvTodayNewInviter.text = invite_num
            }
            yesterday?.apply {
                tvYesterdayTransactionsNumber.text = transactions_num
                tvYesterdayTurnover.text = turnover
                tvYesterdayNewInviter.text = invite_num
            }
            tvTotalInviteNum.text = String.format(
                resources.getString(R.string.total_invite_people_number),
                total_invite_num
            )

            inviteRecordAdapter.setNewData(invitation_list?.data)
        }
    }

}