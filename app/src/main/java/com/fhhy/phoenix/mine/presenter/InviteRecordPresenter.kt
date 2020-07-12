package com.fhhy.phoenix.mine.presenter

import com.fhhy.phoenix.base.BasePresenter
import com.fhhy.phoenix.mine.contract.InviteRecordContract
import com.fhhy.phoenix.mine.model.InviteRecordModel

// Created by admin on 2020/7/12.
class InviteRecordPresenter :
    BasePresenter<InviteRecordContract.Model, InviteRecordContract.View>(),
    InviteRecordContract.Presenter {
    override fun createModel(): InviteRecordContract.Model? = InviteRecordModel()
}