package com.fhhy.phoenix.http

import com.fhhy.phoenix.bean.HomeCurrenciesList
import com.fhhy.phoenix.bean.HomeTopWrapBean
import com.fhhy.phoenix.bean.HttpResult
import io.reactivex.Observable
import io.reactivex.Single
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
    fun requestCheckCode(@FieldMap map: Map<String, String?>): Observable<HttpResult<Any?>>

    //首页币价
    @POST("index/currency_price_list")
    fun requestHomeCurrencies(): Observable<HttpResult<HomeCurrenciesList>>

    //首页币价
    @POST("index/currency_price_list")
    fun requestHomeCurrenciesSingle(): Single<HttpResult<HomeCurrenciesList>>

    //首页banner、消息轮播、四个btn 连接
    @POST("index/index")
    fun requestHomeTopData(): Observable<HttpResult<HomeTopWrapBean>>
}