package com.fhhy.phoenix.mine.presenter

import com.fhhy.phoenix.base.BasePresenter
import com.fhhy.phoenix.mine.contract.AddNewAddressContract
import com.fhhy.phoenix.mine.model.AddNewAddressModel

// Created by admin on 2020/6/21.
class AddNewsAddressPresenter :
    BasePresenter<AddNewAddressContract.Model, AddNewAddressContract.View>(),
    AddNewAddressContract.Presenter {
    override fun createModel(): AddNewAddressContract.Model? = AddNewAddressModel()
}