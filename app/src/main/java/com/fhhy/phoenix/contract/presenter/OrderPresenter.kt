package com.fhhy.phoenix.contract.presenter

import com.fhhy.phoenix.base.BasePresenter
import com.fhhy.phoenix.contract.OrderContract
import com.fhhy.phoenix.contract.model.OrderModel

// Created by admin on 2020/6/26.
class OrderPresenter :
    BasePresenter<OrderContract.Model, OrderContract.View>(),
    OrderContract.Presenter {
    override fun createModel(): OrderContract.Model? = OrderModel()
}