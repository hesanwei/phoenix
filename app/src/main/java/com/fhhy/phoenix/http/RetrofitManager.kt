package com.fhhy.phoenix.http

import HeaderInterceptor
import QueryParameterInterceptor
import SaveCookieInterceptor
import com.fhhy.phoenix.BuildConfig
import com.fhhy.phoenix.constants.Constants
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * AMRetrofitManager class
 *
 * @author hesanwei created on 2020/1/13
 *
 */
object RetrofitManager {

    private var retrofit: Retrofit? = null

    val apiService: ApiService by lazy { createRetrofit().create(ApiService::class.java) }

    private fun createRetrofit(): Retrofit {
        if (retrofit == null) {
            // 设置Gson宽松模式，应对服务器json格式不规范问题
            val gson = GsonBuilder().setLenient()
                .create()
            retrofit = Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)  // baseUrl
                .client(getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create(gson))
//                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        }
        return retrofit!!
    }

    /**
     * 获取 OkHttpClient
     */
    private fun getOkHttpClient(): OkHttpClient {
        val builder = OkHttpClient().newBuilder()

        //设置 请求的缓存的大小跟位置
//        val cacheFile = File(App.context.cacheDir, "cache")
//        val cache = Cache(cacheFile, HttpConstant.MAX_CACHE_SIZE)
        builder.run {
//            addInterceptor(RequestInterceptor())
            addInterceptor(HeaderInterceptor())
            addInterceptor(HttpLoggingInterceptor().apply {
                level =
                    if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BASIC else HttpLoggingInterceptor.Level.NONE
            })
            addInterceptor(QueryParameterInterceptor())
            addInterceptor(SaveCookieInterceptor())
//            addInterceptor(CacheInterceptor())
//            cache(cache)  //添加缓存
//            connectTimeout(Constants.DEFAULT_TIMEOUT, TimeUnit.SECONDS)
//            readTimeout(Constants.DEFAULT_TIMEOUT, TimeUnit.SECONDS)
//            writeTimeout(Constants.DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            retryOnConnectionFailure(true) // 错误重连
            // cookieJar(CookieManager())
        }
        return builder.build()
    }
}