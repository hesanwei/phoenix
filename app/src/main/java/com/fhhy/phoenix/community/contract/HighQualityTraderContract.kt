package com.fhhy.phoenix.community.contract

import com.fhhy.phoenix.base.IModel
import com.fhhy.phoenix.base.IPresenter
import com.fhhy.phoenix.base.IView

/**
 * Created by hecuncun on 2020/7/5
 */
interface HighQualityTraderContract {
    interface View : IView
    interface Model : IModel
    interface Presenter : IPresenter<View>
}