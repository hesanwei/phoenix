package com.fhhy.phoenix.community

import com.fhhy.phoenix.base.IModel
import com.fhhy.phoenix.base.IPresenter
import com.fhhy.phoenix.base.IView

// Created by admin on 2020/6/7.
interface CommunityContract {
    interface View : IView
    interface Model : IModel
    interface Presenter : IPresenter<View>
}