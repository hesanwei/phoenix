package com.fhhy.phoenix.mine.model

import com.fhhy.phoenix.base.BaseModel
import com.fhhy.phoenix.bean.CountryListBean
import com.fhhy.phoenix.bean.HttpResult
import com.fhhy.phoenix.http.RetrofitManager
import com.fhhy.phoenix.mine.contract.CountryContract
import io.reactivex.Observable

// Created by admin on 2020/7/12.
class CountryModel : BaseModel(), CountryContract.Model {
    override fun requestCountryList(): Observable<HttpResult<MutableList<CountryListBean>?>> {
        return RetrofitManager.apiService.requestCountryList()
    }
}