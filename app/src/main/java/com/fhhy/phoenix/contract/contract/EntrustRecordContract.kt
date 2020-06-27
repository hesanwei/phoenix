package com.fhhy.phoenix.contract.contract

import com.fhhy.phoenix.base.IModel
import com.fhhy.phoenix.base.IPresenter
import com.fhhy.phoenix.base.IView

// Created by admin on 2020/6/27.
interface EntrustRecordContract {
    interface View : IView
    interface Model : IModel
    interface Presenter : IPresenter<View>
}