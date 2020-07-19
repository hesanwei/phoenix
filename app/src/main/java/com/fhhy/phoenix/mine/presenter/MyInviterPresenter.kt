package com.fhhy.phoenix.mine.presenter

import com.fhhy.phoenix.base.BasePresenter
import com.fhhy.phoenix.mine.contract.MyInviterContract
import com.fhhy.phoenix.mine.model.MyInviterModel
import request

// Created by admin on 2020/7/19.
class MyInviterPresenter : BasePresenter<MyInviterContract.Model, MyInviterContract.View>(),
    MyInviterContract.Presenter {

    override fun createModel(): MyInviterContract.Model? = MyInviterModel()

    override fun requestMyInviter() {
        mModel?.requestMyInviter()?.request(mModel, mView) {
            mView?.requestMyInviterSuccess(it.data)
        }
    }

    override fun requestAcceptInvite(invitation_code: String) {
        mModel?.requestAcceptInvite(invitation_code)?.request(mModel, mView) {
            mView?.requestAcceptInviteSuccess()
        }
    }

}