package com.fhhy.phoenix.mine.presenter

import com.fhhy.phoenix.base.BasePresenter
import com.fhhy.phoenix.mine.contract.CountryContract
import com.fhhy.phoenix.mine.model.CountryModel
import request

// Created by admin on 2020/7/12.
class CountryPresenter : BasePresenter<CountryContract.Model, CountryContract.View>(),
    CountryContract.Presenter {

    override fun createModel(): CountryContract.Model? = CountryModel()

    override fun requestCountryList() {
        mModel?.requestCountryList()?.request(mModel, mView) {
            mView?.requestCountryListSuccess(it.data)
        }
    }
}