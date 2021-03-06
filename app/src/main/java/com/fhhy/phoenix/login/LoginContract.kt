package com.fhhy.phoenix.login

import com.fhhy.phoenix.base.IModel
import com.fhhy.phoenix.base.IPresenter
import com.fhhy.phoenix.base.IView
import com.fhhy.phoenix.bean.HttpResult
import com.fhhy.phoenix.bean.LoginBean
import io.reactivex.Observable

// Created by admin on 2020/6/7.
interface LoginContract {
    interface View : IView {
        fun requestCheckCodeSuccess()
        fun requestLoginSuccess(mobile: String, loginBean: LoginBean?)
        fun requestUpdatePwdSuccess()
    }

    interface Model : IModel {
        fun requestCheckCode(mobile: String?, captcha: String?): Observable<HttpResult<Any?>>
        fun requestUpdatePwdCheckCode(mobile: String?, captcha: String?): Observable<HttpResult<Any?>>
        fun requestLogin(
            mobile: String,
            password: String?,
            code: String? = "",
            google_code: String? = ""
        ): Observable<HttpResult<LoginBean?>>

        fun requestRegister(
            mobile: String,
            password: String?,
            code: String?,
            invitation_code: String?
        ): Observable<HttpResult<LoginBean?>>

        fun requestUpdatePwd(
            code: String?,
            password: String?
        ): Observable<HttpResult<Any?>>
    }

    interface Presenter : IPresenter<View> {
        fun requestCheckCode(mobile: String?, captcha: String?)
        fun requestUpdatePwdCheckCode(mobile: String?, captcha: String?)

        fun requestLogin(
            mobile: String,
            password: String?,
            code: String? = "",
            google_code: String? = ""
        )

        fun requestRegister(
            mobile: String,
            password: String?,
            code: String?,
            invitation_code: String?
        )

        fun requestUpdatePwd(
            code: String?,
            password: String?
        )

    }
}