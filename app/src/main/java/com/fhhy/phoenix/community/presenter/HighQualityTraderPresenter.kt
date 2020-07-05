package com.fhhy.phoenix.community.presenter

import com.fhhy.phoenix.base.BasePresenter
import com.fhhy.phoenix.community.contract.HighQualityTraderContract
import com.fhhy.phoenix.community.contract.SearchTraderContract
import com.fhhy.phoenix.community.contract.TraderInfoContract
import com.fhhy.phoenix.community.model.HighQualityTraderModel
import com.fhhy.phoenix.community.model.SearchTraderModel
import com.fhhy.phoenix.community.model.TraderInfoModel

/**
 * Created by hecuncun on 2020/7/5
 */
class HighQualityTraderPresenter:BasePresenter<HighQualityTraderContract.Model,HighQualityTraderContract.View>(),HighQualityTraderContract.Presenter {
    override fun createModel(): HighQualityTraderContract.Model? =HighQualityTraderModel()
}