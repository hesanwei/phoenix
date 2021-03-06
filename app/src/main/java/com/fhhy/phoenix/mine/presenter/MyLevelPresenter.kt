package com.fhhy.phoenix.mine.presenter

import com.fhhy.phoenix.base.BasePresenter
import com.fhhy.phoenix.mine.contract.MyLevelContract
import com.fhhy.phoenix.mine.model.MyLevelModel
import request

// Created by admin on 2020/7/12.
class MyLevelPresenter : BasePresenter<MyLevelContract.Model, MyLevelContract.View>(),
    MyLevelContract.Presenter {
    override fun createModel(): MyLevelContract.Model? = MyLevelModel()
    override fun requestMyLevel() {
        mModel?.requestMyLevel()?.request(mModel, mView) {
            mView?.requestMyLevelSuccess(it.data)
        }
    }
}