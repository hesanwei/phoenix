package com.fhhy.phoenix.mine.contract

import com.fhhy.phoenix.base.IModel
import com.fhhy.phoenix.base.IPresenter
import com.fhhy.phoenix.base.IView
import com.fhhy.phoenix.bean.BonusBean
import com.fhhy.phoenix.bean.HttpResult
import io.reactivex.Observable

// Created by admin on 2020/7/19.
interface MyBonusContract {
    interface View : IView {
        fun requestBonusListSuccess(dataList: List<BonusBean>?)
    }

    interface Model : IModel {
        fun requestBonusList(type: String): Observable<HttpResult<List<BonusBean>?>>
    }

    interface Presenter : IPresenter<View> {
        fun requestBonusList(type: String)
    }
}