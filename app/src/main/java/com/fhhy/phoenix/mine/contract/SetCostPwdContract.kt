package com.fhhy.phoenix.mine.contract

import com.fhhy.phoenix.base.IModel
import com.fhhy.phoenix.base.IPresenter
import com.fhhy.phoenix.base.IView
import com.fhhy.phoenix.bean.HttpResult
import io.reactivex.Observable

// Created by admin on 2020/6/14.
interface SetCostPwdContract {
    interface View : IView {
        fun requestSetCostPwdSuccess()
    }

    interface Model : IModel {
        fun requestSetCostPwd(paypwd: String, repaypwd: String): Observable<HttpResult<Any?>>
    }

    interface Presenter : IPresenter<View> {
        fun requestSetCostPwd(paypwd: String, repaypwd: String)
    }
}