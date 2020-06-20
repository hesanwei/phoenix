package com.fhhy.phoenix.mine.presenter

import com.fhhy.phoenix.base.BasePresenter
import com.fhhy.phoenix.mine.contract.RechargeContract
import com.fhhy.phoenix.mine.model.RechargeModel

// Created by admin on 2020/6/20.
class RechargePresenter : BasePresenter<RechargeContract.Model, RechargeContract.View>(),
    RechargeContract.Presenter {
    override fun createModel(): RechargeContract.Model? = RechargeModel()
}