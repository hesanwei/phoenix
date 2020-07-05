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
    override fun requestCheckCode(mobile: String?, captcha: String?) {
        mModel?.requestCheckCode(mobile, captcha)?.request(mModel, mView) {
            mView?.requestCheckCodeSuccess()
        }
    }

    override fun requestLogin(
        mobile: String,
        password: String?,
        code: String?,
        invitation_code: String?,
        google_code: String?
    ) {
        mModel?.requestLogin(mobile, password, code, invitation_code, google_code)
            ?.request(mModel, mView) {
                mView?.requestLoginSuccess(mobile,it.data)
            }
    }
}