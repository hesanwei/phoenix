package com.fhhy.phoenix.login.presenter

import com.fhhy.phoenix.base.BasePresenter
import com.fhhy.phoenix.home.HomeContract
import com.fhhy.phoenix.home.model.HomeModel
import com.fhhy.phoenix.login.LoginContract
import com.fhhy.phoenix.login.State
import com.fhhy.phoenix.login.model.LoginModel
import request

// Created by admin on 2020/6/7.
class LoginPresenter : BasePresenter<LoginContract.Model, LoginContract.View>(),
    LoginContract.Presenter {

    override fun createModel(): LoginContract.Model? = LoginModel()
    override fun requestCheckCode(currentState: State, mobile: String?) {
        mModel?.requestCheckCode(mobile)?.request(mModel, mView) {
            mView?.requestCheckCodeSuccess(currentState)
        }
    }
}