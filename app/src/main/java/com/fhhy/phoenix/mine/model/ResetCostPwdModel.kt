package com.fhhy.phoenix.mine.model

import com.fhhy.phoenix.base.BaseModel
import com.fhhy.phoenix.bean.HttpResult
import com.fhhy.phoenix.http.RetrofitManager
import com.fhhy.phoenix.mine.contract.ResetCostPwdContract
import getRequestMap
import io.reactivex.Observable

// Created by admin on 2020/6/14.
class ResetCostPwdModel: BaseModel(), ResetCostPwdContract.Model {
    override fun requestUpdateCostPwd(
        code: String?,
        paypwd: String?
    ): Observable<HttpResult<Any?>> {
        val requestMap = getRequestMap()
        requestMap["code"] = code
        requestMap["paypwd"] = paypwd
        return RetrofitManager.apiService.requestResetCostPwd(requestMap)
    }
}