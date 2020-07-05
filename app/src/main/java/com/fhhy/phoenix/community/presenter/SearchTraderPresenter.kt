package com.fhhy.phoenix.community.presenter

import com.fhhy.phoenix.base.BasePresenter
import com.fhhy.phoenix.community.contract.SearchTraderContract
import com.fhhy.phoenix.community.model.SearchTraderModel

/**
 * Created by hecuncun on 2020/7/5
 */
class SearchTraderPresenter:BasePresenter<SearchTraderContract.Model,SearchTraderContract.View>(),SearchTraderContract.Presenter {
    override fun createModel(): SearchTraderContract.Model? =SearchTraderModel()
}