package com.fhhy.phoenix.home

import com.fhhy.phoenix.base.IModel
import com.fhhy.phoenix.base.IPresenter
import com.fhhy.phoenix.base.IView
import com.fhhy.phoenix.bean.*
import io.reactivex.Observable
import io.reactivex.Single

// Created by admin on 2020/6/7.
interface HomeContract {
    interface View : IView {
        fun setHomeCurrencies(currencies: List<CurrencyPrice>)
        fun showBannerAndNav(topWrapBean: HomeTopWrapBean)
        fun updateMsgUnReadNum(num: Int)
    }
    interface Model : IModel {
        fun requestHomeTopData() : Observable<HttpResult<HomeTopWrapBean>>
        fun requestHomeCurrencies() : Observable<HttpResult<HomeCurrenciesList>>
        fun requestMsgUnReadNum() : Observable<HttpResult<UnReadNum>>
    }
    interface Presenter : IPresenter<View> {
        fun requestHomeData()
        fun updateMsgUnReadNum()
    }
}