package com.fhhy.phoenix.mine.model

import android.text.TextUtils
import com.fhhy.phoenix.base.BaseModel
import com.fhhy.phoenix.bean.HttpResult
import com.fhhy.phoenix.bean.UploadBean
import com.fhhy.phoenix.bean.UserInfoBean
import com.fhhy.phoenix.http.RetrofitManager
import com.fhhy.phoenix.mine.contract.PersonalInfoContract
import getRequestMap
import io.reactivex.Observable
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

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

    override fun requestUploadAvatar(path: String): Observable<HttpResult<UploadBean?>> {
        val builder = MultipartBody.Builder()
        builder.setType(MultipartBody.FORM)
        val file = File(path)
        val requestBody: RequestBody =
            RequestBody.create(MediaType.parse("multipart/form-data"), file)
        builder.addFormDataPart("file", file.name, requestBody)
        return RetrofitManager.apiService.requestUpload(builder.build().parts())
    }

}