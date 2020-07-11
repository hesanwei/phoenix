package com.fhhy.phoenix.mine.contract

import com.fhhy.phoenix.base.IModel
import com.fhhy.phoenix.base.IPresenter
import com.fhhy.phoenix.base.IView
import com.fhhy.phoenix.bean.HttpResult
import com.fhhy.phoenix.bean.UserInfoBean
import io.reactivex.Observable

// Created by admin on 2020/7/11.
interface PersonalInfoContract {
    interface View : IView {
        fun requestUserInfoSuccess(userInfoBean: UserInfoBean?)
    }

    interface Model : IModel {
        fun requestUserInfo(): Observable<HttpResult<UserInfoBean?>>
    }

    interface Presenter : IPresenter<View> {
        fun requestUserInfo()
    }
}