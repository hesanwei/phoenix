package com.fhhy.phoenix.community.presenter

import com.fhhy.phoenix.base.BasePresenter
import com.fhhy.phoenix.community.contract.InformationContract
import com.fhhy.phoenix.community.model.InformationModel

/**
 * Created by hecuncun on 2020/7/4
 */
class InformationPresenter: BasePresenter<InformationContract.Model, InformationContract.View>(),InformationContract.Presenter{
    override fun createModel(): InformationContract.Model?=InformationModel()
}