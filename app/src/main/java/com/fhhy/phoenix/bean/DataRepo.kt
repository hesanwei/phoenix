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

data class UserInfoBean(
    val avatar: String?,
    val nick_name: String?,
    val check_status: String?,
    val money: String?,
    val beginner_bonus: String?,
    val idcard_auth: String?,
    val help_center: String?,
    val id: String?,
    val mobile: String?,
    val sex: String?
)
