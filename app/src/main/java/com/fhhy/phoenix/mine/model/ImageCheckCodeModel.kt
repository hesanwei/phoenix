package com.fhhy.phoenix.mine.model

import com.fhhy.phoenix.base.BaseModel
import com.fhhy.phoenix.bean.HttpResult
import com.fhhy.phoenix.http.RetrofitManager
import com.fhhy.phoenix.mine.contract.ImageCheckCodeContract
import getRequestMap
import io.reactivex.Observable

// Created by admin on 2020/6/14.
class ImageCheckCodeModel: BaseModel(),ImageCheckCodeContract.Model {
    override fun requestUpdatePwdCheckCode(
        mobile: String,
        imgCheckCode: String
    ): Observable<HttpResult<Any?>> {
        val requestMap = getRequestMap()
        requestMap["mobile"]=mobile
        requestMap["captcha"]=imgCheckCode
        return RetrofitManager.apiService.requestUpdatePwdCode(requestMap)
    }
}