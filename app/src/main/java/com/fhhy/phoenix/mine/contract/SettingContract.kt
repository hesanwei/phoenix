package com.fhhy.phoenix.mine.contract

import com.fhhy.phoenix.base.IModel
import com.fhhy.phoenix.base.IPresenter
import com.fhhy.phoenix.base.IView
import com.fhhy.phoenix.bean.AppVersionBean
import com.fhhy.phoenix.bean.HttpResult
import io.reactivex.Observable

// Created by admin on 2020/6/14.
interface SettingContract {
    interface View : IView {
        fun getAppVersionSuccess(bean: AppVersionBean?)
        fun requestLogoutSuccess()
    }

    interface Model : IModel {
        fun getAppVersion(): Observable<HttpResult<AppVersionBean?>>
        fun requestLogout(refresh_token: String?): Observable<HttpResult<Any?>>
    }

    interface Presenter : IPresenter<View> {
        fun getAppVersion()
        fun requestLogout(refresh_token: String?)
    }
}