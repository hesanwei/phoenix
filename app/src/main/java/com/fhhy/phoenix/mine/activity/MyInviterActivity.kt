package com.fhhy.phoenix.mine.activity

import android.text.TextUtils
import com.fhhy.phoenix.R
import com.fhhy.phoenix.base.BaseMvpActivity
import com.fhhy.phoenix.bean.MyInviterBean
import com.fhhy.phoenix.mine.contract.MyInviterContract
import com.fhhy.phoenix.mine.presenter.MyInviterPresenter
import kotlinx.android.synthetic.main.activity_my_inviter.*
import showToast

// Created by admin on 2020/7/19.
class MyInviterActivity : BaseMvpActivity<MyInviterContract.View, MyInviterContract.Presenter>(),
    MyInviterContract.View {
    override fun createPresenter(): MyInviterContract.Presenter = MyInviterPresenter()

    override fun getLayoutId(): Int = R.layout.activity_my_inviter

    override fun initView() {
        super.initView()
        mPresenter?.requestMyInviter()
        btnBack.setOnClickListener { finish() }
        tvCommit.setOnClickListener {
            doCommit()
        }
    }

    private fun doCommit() {
        val inviteCode = etInviteCode.text.toString()
        if (TextUtils.isEmpty(inviteCode)) {
            showToast(resources.getString(R.string.input_invite_code_hint))
            return
        }

        mPresenter?.requestAcceptInvite(inviteCode)
    }

    override fun requestMyInviterSuccess(myInviterBean: MyInviterBean?) {
    }

    override fun requestAcceptInviteSuccess() {

    }

}