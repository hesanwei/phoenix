package com.fhhy.phoenix.mine.model

import android.text.TextUtils
import com.fhhy.phoenix.base.BaseModel
import com.fhhy.phoenix.bean.HttpResult
import com.fhhy.phoenix.bean.UserInfoBean
import com.fhhy.phoenix.http.RetrofitManager
import com.fhhy.phoenix.mine.contract.PersonalInfoContract
import getRequestMap
import io.reactivex.Observable

// Created by admin on 2020/7/11.
class PersonalInfoModel : BaseModel(), PersonalInfoContract.Model {
    override fun requestUserInfo(): Observable<HttpResult<UserInfoBean?>> {
        return RetrofitManager.apiService.requestPersonalInfo()
    }

    override fun requestSetPersonalInfo(
        nick_name: String?,
        sex: String?,
        profile: String?,
        country: String?
    ): Observable<HttpResult<Any?>> {
        val requestMap = getRequestMap()
        if (!TextUtils.isEmpty(nick_name)) {
            requestMap["nick_name"] = nick_name
        }
        if (!TextUtils.isEmpty(sex)) {
            requestMap["sex"] = sex
        }
        if (!TextUtils.isEmpty(profile)) {
            requestMap["profile"] = profile
        }
        if (!TextUtils.isEmpty(country)) {
            requestMap["country"] = country
        }

        return RetrofitManager.apiService.requestSerPersonalInfo(requestMap)
    }

}