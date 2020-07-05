package com.fhhy.phoenix.bean

import com.fhhy.phoenix.base.BaseBean

/**
 * DataDepo class
 *
 * @author admin created on 2020/7/5
 *
 */
data class HttpResult<T>(
    val data: T
) : BaseBean()

data class LoginBean(
    val need_sms_code: String?,//是否需要验证码 1需要
    val info: String?
)
