package com.fhhy.phoenix.exchange.presenter

import com.fhhy.phoenix.base.BasePresenter
import com.fhhy.phoenix.exchange.ExchangeContract
import com.fhhy.phoenix.exchange.model.ExchangeModel

// Created by admin on 2020/6/7.
class ExchangePresenter : BasePresenter<ExchangeContract.Model, ExchangeContract.View>(),
    ExchangeContract.Presenter {
    override fun createModel(): ExchangeContract.Model? = ExchangeModel()
}