package com.fhhy.phoenix.mine.presenter

import com.fhhy.phoenix.base.BasePresenter
import com.fhhy.phoenix.mine.contract.FundsTransferContract
import com.fhhy.phoenix.mine.model.FundsTransferModel

// Created by admin on 2020/7/20.
class FundsTransferPresenter :
    BasePresenter<FundsTransferContract.Model, FundsTransferContract.View>(),
    FundsTransferContract.Presenter {
    override fun createModel(): FundsTransferContract.Model? = FundsTransferModel()
}