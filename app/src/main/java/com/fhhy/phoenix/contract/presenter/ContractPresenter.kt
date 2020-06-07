package com.fhhy.phoenix.contract.presenter

import com.fhhy.phoenix.base.BasePresenter
import com.fhhy.phoenix.contract.ContractContract
import com.fhhy.phoenix.contract.model.ContractModel

// Created by admin on 2020/6/7.
class ContractPresenter : BasePresenter<ContractContract.Model, ContractContract.View>(),
    ContractContract.Presenter {
    override fun createModel(): ContractContract.Model? = ContractModel()
}