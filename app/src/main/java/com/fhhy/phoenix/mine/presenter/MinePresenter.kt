package com.fhhy.phoenix.mine.presenter

import com.fhhy.phoenix.base.BasePresenter
import com.fhhy.phoenix.mine.MineContract
import com.fhhy.phoenix.mine.model.MineModel

// Created by admin on 2020/6/7.
class MinePresenter : BasePresenter<MineContract.Model, MineContract.View>(),
    MineContract.Presenter {
    override fun createModel(): MineContract.Model? = MineModel()
}