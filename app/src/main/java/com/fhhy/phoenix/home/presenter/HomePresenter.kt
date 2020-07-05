package com.fhhy.phoenix.home.presenter

import com.fhhy.phoenix.base.BasePresenter
import com.fhhy.phoenix.home.HomeContract
import com.fhhy.phoenix.home.model.HomeModel
import getRequestMap
import request

// Created by admin on 2020/6/7.
class HomePresenter : BasePresenter<HomeContract.Model, HomeContract.View>(),
    HomeContract.Presenter {
    override fun createModel(): HomeContract.Model? = HomeModel()

    private fun requestHomeTopData() {
        mModel?.run {
            requestHomeTopData().request(mModel, mView,isShowLoading = false){httpResult ->
                mView?.showBannerAndNav(httpResult.data)
            }
        }
    }

    private fun requestHomeCurrencies() {
        mModel?.let {
            it.requestHomeCurrencies().request(mModel, mView){httpResult ->
                mView?.setHomeCurrencies(httpResult.data.currencyPriceList)
            }
        }
    }
    override fun requestHomeData() {
        requestHomeTopData()
        requestHomeCurrencies()
    }
}