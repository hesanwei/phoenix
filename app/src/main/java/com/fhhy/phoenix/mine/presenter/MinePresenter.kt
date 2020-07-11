package com.fhhy.phoenix.mine.presenter

import com.fhhy.phoenix.base.BasePresenter
import com.fhhy.phoenix.mine.contract.MineContract
import com.fhhy.phoenix.mine.model.MineModel
import request

// Created by admin on 2020/6/7.
class MinePresenter : BasePresenter<MineContract.Model, MineContract.View>(),
    MineContract.Presenter {
    override fun createModel(): MineContract.Model? = MineModel()
    override fun requestUserInfo() {
        mModel?.requestUserInfo()?.request(mModel, mView) {
            mView?.requestUserInfoSuccess(it.data)
        }
    }
}