package com.fhhy.phoenix.mine.model

import com.fhhy.phoenix.base.BaseModel
import com.fhhy.phoenix.bean.HttpResult
import com.fhhy.phoenix.http.RetrofitManager
import com.fhhy.phoenix.mine.contract.ResetLoginPwdContract
import getRequestMap
import io.reactivex.Observable

// Created by admin on 2020/6/14.
class ResetLoginPwdModel: BaseModel(), ResetLoginPwdContract.Model {
    override fun requestUpdatePwd(code: String?, password: String?): Observable<HttpResult<Any?>> {
        val requestMap = getRequestMap()
        requestMap["code"] = code
        requestMap["password"] = password
        return RetrofitManager.apiService.requestUpdatePwd(requestMap)
    }
}