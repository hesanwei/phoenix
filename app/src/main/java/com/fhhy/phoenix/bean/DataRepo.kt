package com.fhhy.phoenix.bean

import com.fhhy.phoenix.base.BaseBean
import java.io.Serializable

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
    val sex: String,
    val profile: String?,
    val country: String?
)

data class UploadBean(
    val url: String?
)

//国家选择
data class CountryListBean(
    val name: String?,
    val code: String?
) : Serializable

//邀请记录bean
data class InviteRecordBean(
    val today: InviteRecordDayBean?,
    val yesterday: InviteRecordDayBean?,
    val total_invite_num: String?,//总邀请人数
    val invitation_list: InviteListBean?
)

//邀请记录 昨天和今天
data class InviteRecordDayBean(
    val transactions_num: String?,//交易人数
    val turnover: String?,//交易额
    val invite_num: String?//新增邀请人数
)

data class InviteListBean(
    val total: String?,
    val per_page: String?,
    val current_page: String?,
    val last_page: String?,
    val data: MutableList<InviteListDataBean>?
)

data class InviteListDataBean(
    val mobile: String?,
    val level_id: String?,
    val create_time: String?
)
//身份认证
data class AuthenticationBean(
    val urlList :MutableList<String>?
)