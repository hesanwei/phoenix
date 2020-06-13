package com.fhhy.phoenix.login.presenter

import com.fhhy.phoenix.base.BasePresenter
import com.fhhy.phoenix.home.HomeContract
import com.fhhy.phoenix.home.model.HomeModel
import com.fhhy.phoenix.login.LoginContract
import com.fhhy.phoenix.login.model.LoginModel

// Created by admin on 2020/6/7.
class LoginPresenter : BasePresenter<LoginContract.Model, LoginContract.View>(),
    LoginContract.Presenter {

    override fun createModel(): LoginContract.Model? = LoginModel()
}