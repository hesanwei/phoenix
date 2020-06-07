package com.fhhy.phoenix.base

/**
 * BaseBean class
 *
 * @author hesanwei created on 2020/1/13
 *
 */
open class BaseBean {
    //响应状态，返回OK表示成功FAIL表示失败。
    //接口配置异常使用默认数据返回时，action_status返回OK。
    //注意：action_status不描述业务有关的成功或失败，仅表示接口请求是否存在异常。例如登录密码填写错误导致登录失败时action_status依旧为OK。登录结果的成功或失败，以及失败的原因描述会以业务数据部分进行描述。
    var actionStatus: String? = ""
    //是否默认数据，1是0否。当接口配置了异常使用默认数据返回时is_default_data返回1。
    var isDefaultData: String? = ""
    var errorCode: String? = ""
    var errorMsg: String? = ""
    var showErrMsg: String? = ""
    var guid: String? = ""
}