package com.fhhy.phoenix.login

enum class State {
    LOGIN,//登录
    LOGIN_SMS,//登录后还需要验证验证码
    REGISTER_STEP_ONE,//注册第一步
    REGISTER_STEP_TWO,//注册第二部
    FORGOT_PWD//重置密码
}