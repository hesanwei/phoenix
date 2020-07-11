package com.fhhy.phoenix.mine.presenter

import com.fhhy.phoenix.base.BasePresenter
import com.fhhy.phoenix.mine.contract.PersonalInfoContract
import com.fhhy.phoenix.mine.model.PersonalInfoModel
import request

// Created by admin on 2020/7/11.
class PersonalInfoPresenter :
    BasePresenter<PersonalInfoContract.Model, PersonalInfoContract.View>(),
    PersonalInfoContract.Presenter {

    override fun createModel(): PersonalInfoContract.Model? = PersonalInfoModel()

    override fun requestUserInfo() {
        mModel?.requestUserInfo()?.request(mModel, mView) {
            mView?.requestUserInfoSuccess(it.data)
        }
    }

}