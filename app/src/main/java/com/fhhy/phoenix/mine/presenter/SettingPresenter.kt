package com.fhhy.phoenix.mine.presenter

import com.fhhy.phoenix.base.BasePresenter
import com.fhhy.phoenix.mine.contract.AccountFullContract
import com.fhhy.phoenix.mine.contract.SettingContract
import com.fhhy.phoenix.mine.model.AccountFullModel
import com.fhhy.phoenix.mine.model.SettingModel
import request

// Created by admin on 2020/6/14.
class SettingPresenter :
    BasePresenter<SettingContract.Model, SettingContract.View>(),
    SettingContract.Presenter {
    override fun createModel(): SettingContract.Model? = SettingModel()
    override fun getAppVersion() {
        mModel?.getAppVersion()?.request(mModel, mView) {
            mView?.getAppVersionSuccess(it.data)
        }
    }

    override fun requestLogout(refresh_token: String?) {
        mModel?.requestLogout(refresh_token)?.request(mModel, mView) {
            mView?.requestLogoutSuccess()
        }
    }
}