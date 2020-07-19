package com.fhhy.phoenix.mine.contract

import com.fhhy.phoenix.base.IModel
import com.fhhy.phoenix.base.IPresenter
import com.fhhy.phoenix.base.IView
import com.fhhy.phoenix.bean.HttpResult
import com.fhhy.phoenix.bean.LevelBean
import io.reactivex.Observable

// Created by admin on 2020/7/12.
interface MyLevelContract {
    interface View : IView {
        fun requestMyLevelSuccess(levelBean: LevelBean?)
    }

    interface Model : IModel {
        fun requestMyLevel(): Observable<HttpResult<LevelBean?>>
    }

    interface Presenter : IPresenter<View> {
        fun requestMyLevel()
    }
}