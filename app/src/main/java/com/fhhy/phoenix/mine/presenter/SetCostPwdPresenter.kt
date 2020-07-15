package com.fhhy.phoenix.mine.presenter

import com.fhhy.phoenix.base.BasePresenter
import com.fhhy.phoenix.mine.contract.SetCostPwdContract
import com.fhhy.phoenix.mine.model.SetCostPwdModel
import request

// Created by admin on 2020/6/14.
class SetCostPwdPresenter :
    BasePresenter<SetCostPwdContract.Model, SetCostPwdContract.View>(),
    SetCostPwdContract.Presenter {
    override fun createModel(): SetCostPwdContract.Model? = SetCostPwdModel()
    override fun requestSetCostPwd(paypwd: String, repaypwd: String) {
        mModel?.requestSetCostPwd(paypwd, repaypwd)?.request(mModel, mView) {
            mView?.requestSetCostPwdSuccess()
        }
    }
}