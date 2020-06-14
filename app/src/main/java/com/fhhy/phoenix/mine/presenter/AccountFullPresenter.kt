package com.fhhy.phoenix.mine.presenter

import com.fhhy.phoenix.base.BasePresenter
import com.fhhy.phoenix.mine.contract.AccountFullContract
import com.fhhy.phoenix.mine.model.AccountFullModel

// Created by admin on 2020/6/14.
class AccountFullPresenter :
    BasePresenter<AccountFullContract.Model, AccountFullContract.View>(),
    AccountFullContract.Presenter {
    override fun createModel(): AccountFullContract.Model? = AccountFullModel()
}