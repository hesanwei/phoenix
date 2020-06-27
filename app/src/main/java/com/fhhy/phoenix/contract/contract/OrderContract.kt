package com.fhhy.phoenix.contract.contract

import com.fhhy.phoenix.base.IModel
import com.fhhy.phoenix.base.IPresenter
import com.fhhy.phoenix.base.IView

// Created by admin on 2020/6/26.
interface OrderContract {
    interface View : IView {}
    interface Model : IModel {}
    interface Presenter : IPresenter<View>
}