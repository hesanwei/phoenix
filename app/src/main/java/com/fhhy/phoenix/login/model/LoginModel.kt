package com.fhhy.phoenix.login.model

import com.fhhy.phoenix.base.BaseModel
import com.fhhy.phoenix.bean.HttpResult
import com.fhhy.phoenix.home.HomeContract
import com.fhhy.phoenix.http.RetrofitManager
import com.fhhy.phoenix.login.LoginContract
import io.reactivex.Observable

// Created by admin on 2020/6/7.
class LoginModel: BaseModel(), LoginContract.Model {
    override fun requestCheckCode(mobile: String?): Observable<HttpResult<Any?>> {
        return RetrofitManager.apiService.requestCheckCode(mobile)
    }
}