package com.fhhy.phoenix.mine.contract

import com.fhhy.phoenix.base.IModel
import com.fhhy.phoenix.base.IPresenter
import com.fhhy.phoenix.base.IView

// Created by admin on 2020/6/21.
interface CoinAddressContract {
    interface View : IView
    interface Model : IModel
    interface Presenter : IPresenter<View>
}