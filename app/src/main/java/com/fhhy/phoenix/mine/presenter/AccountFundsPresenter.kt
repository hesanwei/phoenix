package com.fhhy.phoenix.mine.presenter

import com.fhhy.phoenix.base.BasePresenter
import com.fhhy.phoenix.mine.contract.AccountFundsContract
import com.fhhy.phoenix.mine.model.AccountFundsModel

// Created by admin on 2020/6/14.
class AccountFundsPresenter :
    BasePresenter<AccountFundsContract.Model, AccountFundsContract.View>(),
    AccountFundsContract.Presenter {
    override fun createModel(): AccountFundsContract.Model? = AccountFundsModel()
}