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

    override fun requestUpdatePwdCheckCode(mobile: String?, captcha: String?) {
        mModel?.requestUpdatePwdCheckCode(mobile, captcha)?.request(mModel, mView) {
            mView?.requestCheckCodeSuccess()
        }
    }

    override fun requestRegister(
        mobile: String,
        password: String?,
        code: String?,
        invitation_code: String?
    ) {
        mModel?.requestRegister(mobile, password, code, invitation_code)
            ?.request(mModel, mView) {
                mView?.requestLoginSuccess(mobile, it.data)
            }
    }

    override fun requestUpdatePwd(code: String?, password: String?) {
        mModel?.requestUpdatePwd(code, password)?.request(mModel, mView) {
            mView?.requestUpdatePwdSuccess()
        }
    }

    override fun requestLogin(
        mobile: String,
        password: String?,
        code: String?,
        google_code: String?
    ) {
        mModel?.requestLogin(mobile, password, code, google_code)
            ?.request(mModel, mView) {
                mView?.requestLoginSuccess(mobile, it.data)
            }
    }
}