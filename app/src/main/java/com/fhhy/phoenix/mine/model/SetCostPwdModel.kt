package com.fhhy.phoenix.mine.model

import com.fhhy.phoenix.base.BaseModel
import com.fhhy.phoenix.bean.HttpResult
import com.fhhy.phoenix.http.RetrofitManager
import com.fhhy.phoenix.mine.contract.SetCostPwdContract
import getRequestMap
import io.reactivex.Observable
import retrofit2.Retrofit

// Created by admin on 2020/6/14.
class SetCostPwdModel: BaseModel(), SetCostPwdContract.Model {
    override fun requestSetCostPwd(paypwd: String, repaypwd: String): Observable<HttpResult<Any?>> {
        val requestMap = getRequestMap()
        requestMap["paypwd"]=paypwd
        requestMap["repaypwd"]=repaypwd
        return RetrofitManager.apiService.requestSetCostPwd(requestMap)
    }
}