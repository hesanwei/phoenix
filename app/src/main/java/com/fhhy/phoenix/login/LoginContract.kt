package com.fhhy.phoenix.login

import com.fhhy.phoenix.base.IModel
import com.fhhy.phoenix.base.IPresenter
import com.fhhy.phoenix.base.IView
import com.fhhy.phoenix.bean.HttpResult
import io.reactivex.Observable

// Created by admin on 2020/6/7.
interface LoginContract {
    interface View : IView {
        fun requestCheckCodeSuccess()
        fun requestLoginSuccess(data: Any?)
    }

    interface Model : IModel {
        fun requestCheckCode(mobile: String?, captcha: String?): Observable<HttpResult<Any?>>
        fun requestLogin(
            mobile: String?,
            password: String?,
            code: String? = "",
            invitation_code: String? = "",
            google_code: String? = ""
        ): Observable<HttpResult<Any?>>
    }

    interface Presenter : IPresenter<View> {
        fun requestCheckCode(mobile: String?, captcha: String?)
        fun requestLogin(
            mobile: String?,
            password: String?,
            code: String? = "",
            invitation_code: String? = "",
            google_code: String? = ""
        )
    }
}