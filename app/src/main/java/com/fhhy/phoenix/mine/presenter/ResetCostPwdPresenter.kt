package com.fhhy.phoenix.mine.presenter

import com.fhhy.phoenix.base.BasePresenter
import com.fhhy.phoenix.mine.contract.ResetCostPwdContract
import com.fhhy.phoenix.mine.model.ResetCostPwdModel
import request

// Created by admin on 2020/6/14.
class ResetCostPwdPresenter :
    BasePresenter<ResetCostPwdContract.Model, ResetCostPwdContract.View>(),
    ResetCostPwdContract.Presenter {
    override fun createModel(): ResetCostPwdContract.Model? = ResetCostPwdModel()
    override fun requestUpdateCostPwd(code: String?, paypwd: String?) {
        mModel?.requestUpdateCostPwd(code,paypwd)?.request(mModel,mView){
            mView?.requestUpdateCostPwdSuccess()
        }
    }
}