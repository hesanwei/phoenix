package com.fhhy.phoenix.mine.presenter

import com.fhhy.phoenix.base.BasePresenter
import com.fhhy.phoenix.mine.contract.ResetLoginPwdContract
import com.fhhy.phoenix.mine.model.ResetLoginPwdModel
import request

// Created by admin on 2020/6/14.
class ResetLoginPwdPresenter :
    BasePresenter<ResetLoginPwdContract.Model, ResetLoginPwdContract.View>(),
    ResetLoginPwdContract.Presenter {
    override fun createModel(): ResetLoginPwdContract.Model? = ResetLoginPwdModel()
    override fun requestUpdatePwd(code: String?, password: String?) {
        mModel?.requestUpdatePwd(code, password)?.request(mModel, mView) {
            mView?.requestUpdatePwdSuccess()
        }
    }
}