package com.fhhy.phoenix.mine.contract

import com.fhhy.phoenix.base.IModel
import com.fhhy.phoenix.base.IPresenter
import com.fhhy.phoenix.base.IView
import com.fhhy.phoenix.bean.CountryListBean
import com.fhhy.phoenix.bean.HttpResult
import io.reactivex.Observable

// Created by admin on 2020/7/12.
interface CountryContract {
    interface View : IView {
        fun requestCountryListSuccess(dataList: MutableList<CountryListBean>?)
    }

    interface Model : IModel {
        fun requestCountryList(): Observable<HttpResult<MutableList<CountryListBean>?>>
    }

    interface Presenter : IPresenter<View> {
        fun requestCountryList()
    }
}