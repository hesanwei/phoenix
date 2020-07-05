package com.fhhy.phoenix.login.model

import com.fhhy.phoenix.base.BaseModel
import com.fhhy.phoenix.bean.HttpResult
import com.fhhy.phoenix.home.HomeContract
import com.fhhy.phoenix.http.RetrofitManager
import com.fhhy.phoenix.login.LoginContract
import getRequestMap
import io.reactivex.Observable

// Created by admin on 2020/6/7.
class LoginModel : BaseModel(), LoginContract.Model {
    override fun requestCheckCode(mobile: String?): Observable<HttpResult<Any?>> {
        val hashMap = getRequestMap()
        hashMap["mobile"] = mobile
        return RetrofitManager.apiService.requestCheckCode(hashMap)
    }
}