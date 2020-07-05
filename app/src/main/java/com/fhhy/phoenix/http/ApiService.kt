package com.fhhy.phoenix.http

import com.fhhy.phoenix.bean.HttpResult
import com.fhhy.phoenix.bean.LoginBean
import io.reactivex.Observable
import okhttp3.HttpUrl
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
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
    fun requestCheckCode(@FieldMap map: Map<String, String?>): Observable<HttpResult<Any?>>

    //获取重置密码的短信验证码
    @FormUrlEncoded
    @POST("index/get_check_code")
    fun requestUpdatePwdCheckCode(@FieldMap map: Map<String, String?>): Observable<HttpResult<Any?>>

    //登录
    @FormUrlEncoded
    @POST("index/login")
    fun requestLogin(@FieldMap map: Map<String, String?>): Observable<HttpResult<LoginBean?>>

    //注册
    @FormUrlEncoded
    @POST("index/register")
    fun requestRegister(@FieldMap map: Map<String, String?>): Observable<HttpResult<LoginBean?>>


    //获取图形验证码
    @POST("index/verify")
    fun requestImgCode(): Observable<ResponseBody>

    //重置密码
    @FormUrlEncoded
    @POST("user/update_password")
    fun requestUpdatePwd(@FieldMap map: Map<String, String?>): Observable<HttpResult<Any?>>
}