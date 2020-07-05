package com.fhhy.phoenix.home

import com.fhhy.phoenix.base.IModel
import com.fhhy.phoenix.base.IPresenter
import com.fhhy.phoenix.base.IView
import com.fhhy.phoenix.bean.CurrencyPrice
import com.fhhy.phoenix.bean.HomeCurrenciesList
import com.fhhy.phoenix.bean.HomeTopWrapBean
import com.fhhy.phoenix.bean.HttpResult
import io.reactivex.Observable
import io.reactivex.Single

// Created by admin on 2020/6/7.
interface HomeContract {
    interface View : IView {
        fun setHomeCurrencies(currencies: List<CurrencyPrice>)
        fun showBannerAndNav(topWrapBean: HomeTopWrapBean)
    }
    interface Model : IModel {
        fun requestHomeTopData() : Observable<HttpResult<HomeTopWrapBean>>
        fun requestHomeCurrencies() : Observable<HttpResult<HomeCurrenciesList>>
    }
    interface Presenter : IPresenter<View> {
        fun requestHomeData()
    }
}