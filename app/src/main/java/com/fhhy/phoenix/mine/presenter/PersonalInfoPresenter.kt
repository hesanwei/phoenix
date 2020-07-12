package com.fhhy.phoenix.mine.presenter

import android.text.TextUtils
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

    override fun requestSetPersonalInfo(
        nick_name: String?,
        sex: String?,
        profile: String?,
        country: String?
    ) {
        mModel?.requestSetPersonalInfo(nick_name, sex, profile, country)?.request(mModel, mView) {
            mView?.requestSetPersonalInfoSuccess(it.message, nick_name, sex, profile, country)
        }
    }

}