package com.fhhy.phoenix.home.model

import com.fhhy.phoenix.base.BaseModel
import com.fhhy.phoenix.bean.HomeCurrenciesList
import com.fhhy.phoenix.bean.HomeTopWrapBean
import com.fhhy.phoenix.bean.HttpResult
import com.fhhy.phoenix.bean.UnReadNum
import com.fhhy.phoenix.home.HomeContract
import com.fhhy.phoenix.http.RetrofitManager
import io.reactivex.Observable
import io.reactivex.Single

// Created by admin on 2020/6/7.
class HomeModel: BaseModel(), HomeContract.Model {
    override fun requestHomeTopData(): Observable<HttpResult<HomeTopWrapBean>> {
        return RetrofitManager.apiService.requestHomeTopData()
    }

    override fun requestHomeCurrencies(): Observable<HttpResult<HomeCurrenciesList>> {
        return RetrofitManager.apiService.requestHomeCurrencies()
    }

    override fun requestMsgUnReadNum(): Observable<HttpResult<UnReadNum>> {
        return RetrofitManager.apiService.requestMsgList()
    }
}