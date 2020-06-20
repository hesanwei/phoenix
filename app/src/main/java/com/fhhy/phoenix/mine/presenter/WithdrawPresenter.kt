package com.fhhy.phoenix.mine.presenter

import com.fhhy.phoenix.base.BasePresenter
import com.fhhy.phoenix.mine.contract.WithdrawContract
import com.fhhy.phoenix.mine.model.WithdrawModel

// Created by admin on 2020/6/20.
class WithdrawPresenter : BasePresenter<WithdrawContract.Model, WithdrawContract.View>(),
    WithdrawContract.Presenter {
    override fun createModel(): WithdrawContract.Model? = WithdrawModel()
}