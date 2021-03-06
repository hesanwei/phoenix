package com.fhhy.phoenix.http

import com.fhhy.phoenix.bean.*
import io.reactivex.Observable
import io.reactivex.Single
import okhttp3.HttpUrl
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*
import okhttp3.ResponseBody


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

    //首页币价
    @POST("index/currency_price_list")
    fun requestHomeCurrencies(): Observable<HttpResult<HomeCurrenciesList>>

    //首页币价
    @POST("index/currency_price_list")
    fun requestHomeCurrenciesSingle(): Single<HttpResult<HomeCurrenciesList>>

    //首页banner、消息轮播、四个btn 连接
    @POST("index/index")
    fun requestHomeTopData(): Observable<HttpResult<HomeTopWrapBean>>

    //我的 用户信息
    @POST("user/index")
    fun requestUserInfo(): Observable<HttpResult<UserInfoBean?>>

    //个人信息
    @POST("user/personal_info")
    fun requestPersonalInfo(): Observable<HttpResult<UserInfoBean?>>

    //设置个人信息
    @FormUrlEncoded
    @POST("user/set_personal_info")
    fun requestSerPersonalInfo(@FieldMap map: Map<String, String?>): Observable<HttpResult<Any?>>

    //文件的上传
    @Multipart
    @POST("user/upload_avatar")
    fun requestUpload(@Part partList: List<MultipartBody.Part>): Observable<HttpResult<UploadBean?>>

    //获取国家列表
    @POST("user/get_country_list")
    fun requestCountryList(): Observable<HttpResult<List<CountryListBean>?>>

    //邀请记录
    @FormUrlEncoded
    @POST("user/my_invitation")
    fun requestInviteRecord(@FieldMap map: Map<String, String?>): Observable<HttpResult<InviteRecordBean?>>

    //身份idCard认证
    @Multipart
    @POST("user/do_real_name")
    fun requestAuthenticationIdCard(
        @PartMap map: Map<String, String?>,
        @Part partList: List<MultipartBody.Part>
    ): Observable<HttpResult<AuthenticationBean?>>

    //消息中心
    @POST("user/message_center")
    fun requestMsgCenterList(): Single<HttpResult<List<MsgCenterBean>>>

    //消息列表
    @FormUrlEncoded
    @POST("user/message")
    fun requestMsgList(@FieldMap map: Map<String, String?>): Single<HttpResult<MsgListPageBean>> //消息列表

    //未读消息数量
    @POST("user/unread_message_num")
    fun requestMsgList(): Observable<HttpResult<UnReadNum>>

    //标记消息已读
    @FormUrlEncoded
    @POST("user/message_read_tag")
    fun consumeMsgRead(@FieldMap map: Map<String, String?>): Single<HttpResult<UnReadNum>>

    //重置/忘记登录密码获取验证码
    @FormUrlEncoded
    @POST("user/get_update_password_code")
    fun requestUpdatePwdCode(@FieldMap map: Map<String, String?>): Observable<HttpResult<Any?>>

    //设置资金密码
    @FormUrlEncoded
    @POST("user/set_pay_password")
    fun requestSetCostPwd(@FieldMap map: Map<String, String?>): Observable<HttpResult<Any?>>

    //重置资金密码获取验证码
    @FormUrlEncoded
    @POST("user/get_forget_pay_code")
    fun requestUpdateCostPwdCode(@FieldMap map: Map<String, String?>): Observable<HttpResult<Any?>>

    //重置资金密码
    @FormUrlEncoded
    @POST("user/forget_pay_password")
    fun requestResetCostPwd(@FieldMap map: Map<String, String?>): Observable<HttpResult<Any?>>

    //安全设置状态
    @POST("user/security_setting")
    fun requestSecuritySettingState(): Observable<HttpResult<SecuritySettingStateBean?>>

    //获取APP版本
    @POST("index/get_app_version")
    fun requestAppVersion(): Observable<HttpResult<AppVersionBean?>>

    //退出登录
    @FormUrlEncoded
    @POST("user/login_out")
    fun requestLogout(@FieldMap map: Map<String, String?>): Observable<HttpResult<Any?>>

    //我的等级
    @POST()
    fun requestMyLevel(): Observable<HttpResult<LevelBean?>>

    //我的赠金
    @FormUrlEncoded
    @POST("user/my_bonus")
    fun requestBonusList(@FieldMap map: Map<String, String?>): Observable<HttpResult<List<BonusBean>?>>

    //我的邀请人
    @POST("user/my_inviter")
    fun requestMyInviter(): Observable<HttpResult<MyInviterBean?>>

    //接收邀请
    @FormUrlEncoded
    @POST("user/set_inviter")
    fun requestAcceptInvite(@FieldMap map: Map<String, String?>): Observable<HttpResult<Any?>>
}