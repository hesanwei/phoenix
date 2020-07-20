package com.fhhy.phoenix.mine.activity

import android.text.TextUtils
import android.view.View
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
        if (myInviterBean?.inviter == null || TextUtils.isEmpty(myInviterBean.inviter.mobile)) {
            clNoInvite.visibility = View.VISIBLE
            clInvite.visibility = View.GONE
            tvTitle.text = resources.getString(R.string.accept_invite)
        } else {
            clNoInvite.visibility = View.GONE
            clInvite.visibility = View.VISIBLE
            tvInviter.text = myInviterBean.inviter.mobile
            tvInviteCode.text = myInviterBean.inviter.invitation_code
            tvTitle.text = resources.getString(R.string.my_inviter)
        }
    }

    override fun showError(errorMsg: String?) {
        super.showError(errorMsg)
        clNoInvite.visibility = View.VISIBLE
        clInvite.visibility = View.GONE
    }

    override fun requestAcceptInviteSuccess() {

    }

}