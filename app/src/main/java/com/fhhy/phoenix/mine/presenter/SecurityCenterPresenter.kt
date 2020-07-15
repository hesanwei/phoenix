package com.fhhy.phoenix.mine.presenter

import com.fhhy.phoenix.base.BasePresenter
import com.fhhy.phoenix.mine.contract.SecurityCenterContract
import com.fhhy.phoenix.mine.model.SecurityCenterModel
import request

// Created by admin on 2020/6/14.
class SecurityCenterPresenter :
    BasePresenter<SecurityCenterContract.Model, SecurityCenterContract.View>(),
    SecurityCenterContract.Presenter {
    override fun createModel(): SecurityCenterContract.Model? = SecurityCenterModel()
    override fun getSecuritySettingState() {
        mModel?.getSecuritySettingState()?.request(mModel,mView){
            mView?.getSecuritySettingStateSuccess(it.data)
        }
    }
}