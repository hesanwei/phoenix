package com.fhhy.phoenix.mine.model

import com.fhhy.phoenix.base.BaseModel
import com.fhhy.phoenix.bean.HttpResult
import com.fhhy.phoenix.bean.UserInfoBean
import com.fhhy.phoenix.http.RetrofitManager
import com.fhhy.phoenix.mine.contract.PersonalInfoContract
import io.reactivex.Observable

// Created by admin on 2020/7/11.
class PersonalInfoModel : BaseModel(), PersonalInfoContract.Model {
    override fun requestUserInfo(): Observable<HttpResult<UserInfoBean?>> {
        return RetrofitManager.apiService.requestPersonalInfo()
    }

}