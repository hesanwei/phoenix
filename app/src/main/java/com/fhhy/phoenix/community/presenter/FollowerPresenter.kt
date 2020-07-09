package com.fhhy.phoenix.community.presenter

import com.fhhy.phoenix.base.BasePresenter
import com.fhhy.phoenix.community.contract.CommunityContract
import com.fhhy.phoenix.community.contract.FollowerContract
import com.fhhy.phoenix.community.contract.HistoricalPositionContract
import com.fhhy.phoenix.community.model.CommunityModel
import com.fhhy.phoenix.community.model.FollowerModel
import com.fhhy.phoenix.community.model.HistoricalPositionModel

// Created by admin on 2020/6/7.
class FollowerPresenter : BasePresenter<FollowerContract.Model, FollowerContract.View>(),
    FollowerContract.Presenter {
    override fun createModel(): FollowerContract.Model? = FollowerModel()
}