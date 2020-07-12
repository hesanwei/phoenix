package com.fhhy.phoenix.mine.presenter

import com.fhhy.phoenix.base.BasePresenter
import com.fhhy.phoenix.mine.contract.InviteContract
import com.fhhy.phoenix.mine.model.InviteModel

// Created by admin on 2020/7/12.
class InvitePresenter : BasePresenter<InviteContract.Model, InviteContract.View>(),
    InviteContract.Presenter {
    override fun createModel(): InviteContract.Model? = InviteModel()
}