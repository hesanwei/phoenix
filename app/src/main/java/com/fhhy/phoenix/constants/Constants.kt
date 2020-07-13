package com.fhhy.phoenix.constants

// Created by admin on 2020/6/7.
object Constants {
    const val CODE_200 = "200"//响应状态码
    const val BASE_URL = "http://120.78.134.196/api/"//测试环境
    const val BASE_IMAGE_URL = "http://120.78.134.196"//测试环境

    const val IMG_CHECK_CODE_URL = BASE_URL + "index/verify" //图形验证码

    //请求码
    const val SELECT_IMAGE_REQUEST_CODE = 0x101//拍照或者选照片
    const val MODIFY_NAME_REQUEST_CODE = 0x102//修改姓名
    const val SELECT_COUNTRY_REQUEST_CODE = 0x103//选择国家地区
}