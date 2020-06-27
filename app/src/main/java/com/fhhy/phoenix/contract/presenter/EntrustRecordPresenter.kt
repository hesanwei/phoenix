package com.fhhy.phoenix.contract.presenter

import com.fhhy.phoenix.base.BasePresenter
import com.fhhy.phoenix.contract.contract.EntrustRecordContract
import com.fhhy.phoenix.contract.model.EntrustRecordModel

// Created by admin on 2020/6/27.
class EntrustRecordPresenter :
    BasePresenter<EntrustRecordContract.Model, EntrustRecordContract.View>(),
    EntrustRecordContract.Presenter {
    override fun createModel(): EntrustRecordContract.Model? = EntrustRecordModel()
}