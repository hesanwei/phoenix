package com.fhhy.phoenix.mine.contract

import com.fhhy.phoenix.base.IModel
import com.fhhy.phoenix.base.IPresenter
import com.fhhy.phoenix.base.IView
import com.fhhy.phoenix.bean.HttpResult
import io.reactivex.Observable

// Created by admin on 2020/6/14.
interface ResetLoginPwdContract {
    interface View : IView{
        fun requestUpdatePwdSuccess()
    }
    interface Model : IModel{
        fun requestUpdatePwd(
            code: String?,
            password: String?
        ): Observable<HttpResult<Any?>>
    }
    interface Presenter : IPresenter<View>{
        fun requestUpdatePwd(
            code: String?,
            password: String?
        )
    }
}