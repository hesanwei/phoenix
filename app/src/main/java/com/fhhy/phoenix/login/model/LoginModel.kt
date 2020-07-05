package com.fhhy.phoenix.login.model

import com.fhhy.phoenix.base.BaseModel
import com.fhhy.phoenix.bean.HttpResult
import com.fhhy.phoenix.bean.LoginBean
import com.fhhy.phoenix.home.HomeContract
import com.fhhy.phoenix.http.RetrofitManager
import com.fhhy.phoenix.login.LoginContract
import getRequestMap
import io.reactivex.Observable

// Created by admin on 2020/6/7.
class LoginModel : BaseModel(), LoginContract.Model {
    override fun requestCheckCode(mobile: String?, captcha: String?): Observable<HttpResult<Any?>> {
        val requestMap = getRequestMap()
        requestMap["mobile"] = mobile
        requestMap["captcha"] = captcha
        return RetrofitManager.apiService.requestCheckCode(requestMap)
    }

    override fun requestLogin(
        mobile: String,
        password: String?,
        code: String?,
        google_code: String?
    ): Observable<HttpResult<LoginBean?>> {
        val requestMap = getRequestMap()
        requestMap["mobile"] = mobile
        requestMap["password"] = password
        requestMap["code"] = code
        requestMap["google_code"] = google_code
        return RetrofitManager.apiService.requestLogin(requestMap)
    }

    override fun requestRegister(
        mobile: String,
        password: String?,
        code: String?,
        invitation_code: String?
    ): Observable<HttpResult<LoginBean?>> {
        val requestMap = getRequestMap()
        requestMap["mobile"] = mobile
        requestMap["password"] = password
        requestMap["code"] = code
        requestMap["invitation_code"] = invitation_code
        return RetrofitManager.apiService.requestRegister(requestMap)
    }

}