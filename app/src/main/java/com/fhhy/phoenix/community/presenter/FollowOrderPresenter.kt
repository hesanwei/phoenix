package com.fhhy.phoenix.community.presenter

import com.fhhy.phoenix.base.BasePresenter
import com.fhhy.phoenix.community.contract.FollowOrderContract
import com.fhhy.phoenix.community.contract.InformationContract
import com.fhhy.phoenix.community.model.FollowOrderModel
import com.fhhy.phoenix.community.model.InformationModel

/**
 * Created by hecuncun on 2020/7/4
 */
class FollowOrderPresenter: BasePresenter<FollowOrderContract.Model, FollowOrderContract.View>(),FollowOrderContract.Presenter{
    override fun createModel(): FollowOrderContract.Model?=FollowOrderModel()
}