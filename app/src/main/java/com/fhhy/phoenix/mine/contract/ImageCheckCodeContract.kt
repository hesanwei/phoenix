package com.fhhy.phoenix.mine.contract

import com.fhhy.phoenix.base.IModel
import com.fhhy.phoenix.base.IPresenter
import com.fhhy.phoenix.base.IView
import com.fhhy.phoenix.bean.HttpResult
import io.reactivex.Observable

// Created by admin on 2020/6/14.
interface ImageCheckCodeContract {
    interface View : IView{
      fun requestUpdatePwdCheckCodeSuccess()
    }
    interface Model : IModel{
        fun requestUpdatePwdCheckCode(mobile: String, imgCheckCode: String): Observable<HttpResult<Any?>>
    }
    interface Presenter : IPresenter<View> {
        fun requestUpdatePwdCheckCode(mobile: String, imgCheckCode: String)
    }
}