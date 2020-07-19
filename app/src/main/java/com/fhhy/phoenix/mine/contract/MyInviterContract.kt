package com.fhhy.phoenix.mine.contract

import com.fhhy.phoenix.base.IModel
import com.fhhy.phoenix.base.IPresenter
import com.fhhy.phoenix.base.IView
import com.fhhy.phoenix.bean.HttpResult
import com.fhhy.phoenix.bean.MyInviterBean
import io.reactivex.Observable

// Created by admin on 2020/7/19.
interface MyInviterContract {
    interface View : IView {
        fun requestMyInviterSuccess(myInviterBean: MyInviterBean?)
        fun requestAcceptInviteSuccess()
    }

    interface Model : IModel {
        fun requestMyInviter(): Observable<HttpResult<MyInviterBean?>>
        fun requestAcceptInvite(invitation_code: String): Observable<HttpResult<Any?>>
    }

    interface Presenter : IPresenter<View> {
        fun requestMyInviter()
        fun requestAcceptInvite(invitation_code: String)
    }
}