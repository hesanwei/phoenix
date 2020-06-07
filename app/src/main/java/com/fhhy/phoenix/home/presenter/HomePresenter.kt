package com.fhhy.phoenix.home.presenter

import com.fhhy.phoenix.base.BasePresenter
import com.fhhy.phoenix.home.HomeContract
import com.fhhy.phoenix.home.model.HomeModel

// Created by admin on 2020/6/7.
class HomePresenter : BasePresenter<HomeContract.Model, HomeContract.View>(),
    HomeContract.Presenter {
    override fun createModel(): HomeContract.Model? = HomeModel()
}