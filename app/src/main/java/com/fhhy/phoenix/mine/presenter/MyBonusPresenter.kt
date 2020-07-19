package com.fhhy.phoenix.mine.presenter

import com.fhhy.phoenix.base.BasePresenter
import com.fhhy.phoenix.mine.contract.MyBonusContract
import com.fhhy.phoenix.mine.model.MyBonusModel
import request

// Created by admin on 2020/7/19.
class MyBonusPresenter : BasePresenter<MyBonusContract.Model, MyBonusContract.View>(),
    MyBonusContract.Presenter {

    override fun createModel(): MyBonusContract.Model? = MyBonusModel()

    override fun requestBonusList(type: String) {
        mModel?.requestBonusList(type)?.request(mModel, mView) {
            mView?.requestBonusListSuccess(it.data)
        }
    }
}