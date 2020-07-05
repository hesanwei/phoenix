package com.fhhy.phoenix.http

import com.fhhy.phoenix.bean.HttpResult
import io.reactivex.Observable
import okhttp3.HttpUrl
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*

/**
 * API class
 *
 * @author hesanwei created on 2020/1/13
 *
 */
interface ApiService {

    //获取短信验证码
    @FormUrlEncoded
    @POST("index/get_check_code")
    fun requestCheckCode(@Field("mobile") mobile: String?): Observable<HttpResult<Any?>>
}