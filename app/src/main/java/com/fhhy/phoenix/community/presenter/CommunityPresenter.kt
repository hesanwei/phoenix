package com.fhhy.phoenix.community.presenter

import com.fhhy.phoenix.base.BasePresenter
import com.fhhy.phoenix.community.contract.CommunityContract
import com.fhhy.phoenix.community.model.CommunityModel

// Created by admin on 2020/6/7.
class CommunityPresenter : BasePresenter<CommunityContract.Model, CommunityContract.View>(),
    CommunityContract.Presenter {
    override fun createModel(): CommunityContract.Model? = CommunityModel()
}