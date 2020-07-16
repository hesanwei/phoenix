package com.fhhy.phoenix.mine.model

import com.fhhy.phoenix.base.BaseModel
import com.fhhy.phoenix.bean.AppVersionBean
import com.fhhy.phoenix.bean.HttpResult
import com.fhhy.phoenix.http.RetrofitManager
import com.fhhy.phoenix.mine.contract.AccountFullContract
import com.fhhy.phoenix.mine.contract.SettingContract
import io.reactivex.Observable

// Created by admin on 2020/6/14.
class SettingModel: BaseModel(), SettingContract.Model {
    override fun getAppVersion(): Observable<HttpResult<AppVersionBean?>> {
        return RetrofitManager.apiService.requestAppVersion()
    }
}