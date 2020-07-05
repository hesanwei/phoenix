package com.fhhy.phoenix.login

import com.fhhy.phoenix.base.IModel
import com.fhhy.phoenix.base.IPresenter
import com.fhhy.phoenix.base.IView
import com.fhhy.phoenix.bean.HttpResult
import io.reactivex.Observable

// Created by admin on 2020/6/7.
interface LoginContract {
    interface View : IView {
        fun requestCheckCodeSuccess(currentState: State)
    }

    interface Model : IModel {
        fun requestCheckCode(mobile: String?): Observable<HttpResult<Any?>>
    }

    interface Presenter : IPresenter<View> {
        fun requestCheckCode(currentState: State, mobile: String?)
    }
}