package com.fhhy.phoenix.mine.model

import com.fhhy.phoenix.base.BaseModel
import com.fhhy.phoenix.bean.HttpResult
import com.fhhy.phoenix.bean.SecuritySettingStateBean
import com.fhhy.phoenix.http.RetrofitManager
import com.fhhy.phoenix.mine.contract.SecurityCenterContract
import io.reactivex.Observable

// Created by admin on 2020/6/14.
class SecurityCenterModel: BaseModel(), SecurityCenterContract.Model {
    override fun getSecuritySettingState(): Observable<HttpResult<SecuritySettingStateBean?>> {
       return RetrofitManager.apiService.requestSecuritySettingState()
    }
}