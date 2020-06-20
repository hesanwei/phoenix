package com.fhhy.phoenix.mine.presenter

import com.fhhy.phoenix.base.BasePresenter
import com.fhhy.phoenix.mine.contract.FullAccountDetailContract
import com.fhhy.phoenix.mine.model.FullAccountDetailModel

// Created by admin on 2020/6/20.
class FullAccountDetailPresenter :
    BasePresenter<FullAccountDetailContract.Model, FullAccountDetailContract.View>(),
    FullAccountDetailContract.Presenter {
    override fun createModel(): FullAccountDetailContract.Model? = FullAccountDetailModel()
}