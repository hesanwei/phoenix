package com.fhhy.phoenix.mine.model

import com.fhhy.phoenix.base.BaseModel
import com.fhhy.phoenix.bean.BonusBean
import com.fhhy.phoenix.bean.HttpResult
import com.fhhy.phoenix.http.RetrofitManager
import com.fhhy.phoenix.mine.contract.MyBonusContract
import getRequestMap
import io.reactivex.Observable

// Created by admin on 2020/7/19.
class MyBonusModel : BaseModel(), MyBonusContract.Model {
    override fun requestBonusList(type: String): Observable<HttpResult<List<BonusBean>?>> {
        val requestMap = getRequestMap()
        requestMap["type"] = type
        return RetrofitManager.apiService.requestBonusList(requestMap)
    }
}