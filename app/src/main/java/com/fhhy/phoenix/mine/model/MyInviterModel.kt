package com.fhhy.phoenix.mine.model

import com.fhhy.phoenix.base.BaseModel
import com.fhhy.phoenix.bean.HttpResult
import com.fhhy.phoenix.bean.MyInviterBean
import com.fhhy.phoenix.http.RetrofitManager
import com.fhhy.phoenix.mine.contract.MyInviterContract
import getRequestMap
import io.reactivex.Observable

// Created by admin on 2020/7/19.
class MyInviterModel : BaseModel(), MyInviterContract.Model {
    override fun requestMyInviter(): Observable<HttpResult<MyInviterBean?>> {
        return RetrofitManager.apiService.requestMyInviter()
    }

    override fun requestAcceptInvite(invitation_code: String): Observable<HttpResult<Any?>> {
        val requestMap = getRequestMap()
        requestMap["invitation_code"] = invitation_code
        return RetrofitManager.apiService.requestAcceptInvite(requestMap)
    }

}