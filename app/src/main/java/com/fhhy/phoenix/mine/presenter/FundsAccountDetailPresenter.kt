package com.fhhy.phoenix.mine.presenter

import com.fhhy.phoenix.base.BasePresenter
import com.fhhy.phoenix.mine.contract.FundsAccountDetailContract
import com.fhhy.phoenix.mine.model.FundsAccountDetailModel

// Created by admin on 2020/6/14.
class FundsAccountDetailPresenter :
    BasePresenter<FundsAccountDetailContract.Model, FundsAccountDetailContract.View>(),
    FundsAccountDetailContract.Presenter {
    override fun createModel(): FundsAccountDetailContract.Model? = FundsAccountDetailModel()
}