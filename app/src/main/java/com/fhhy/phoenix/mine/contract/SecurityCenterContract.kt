package com.fhhy.phoenix.mine.contract

import com.fhhy.phoenix.base.IModel
import com.fhhy.phoenix.base.IPresenter
import com.fhhy.phoenix.base.IView
import com.fhhy.phoenix.bean.HttpResult
import com.fhhy.phoenix.bean.SecuritySettingStateBean
import io.reactivex.Observable

// Created by admin on 2020/6/14.
interface SecurityCenterContract {
    interface View : IView{
        fun getSecuritySettingStateSuccess(bean :SecuritySettingStateBean?)
    }
    interface Model : IModel{
        fun getSecuritySettingState():Observable<HttpResult<SecuritySettingStateBean?>>
    }
    interface Presenter : IPresenter<View> {
        fun getSecuritySettingState()
    }
}