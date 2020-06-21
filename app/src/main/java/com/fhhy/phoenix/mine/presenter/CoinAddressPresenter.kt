package com.fhhy.phoenix.mine.presenter

import com.fhhy.phoenix.base.BasePresenter
import com.fhhy.phoenix.mine.contract.CoinAddressContract
import com.fhhy.phoenix.mine.model.CoinAddressModel

// Created by admin on 2020/6/21.
class CoinAddressPresenter : BasePresenter<CoinAddressContract.Model, CoinAddressContract.View>(),
    CoinAddressContract.Presenter {
    override fun createModel(): CoinAddressContract.Model? = CoinAddressModel()
}