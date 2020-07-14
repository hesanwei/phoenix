package com.fhhy.phoenix.mine.presenter

import com.fhhy.phoenix.base.BasePresenter
import com.fhhy.phoenix.mine.contract.AccountFullContract
import com.fhhy.phoenix.mine.contract.ImageCheckCodeContract
import com.fhhy.phoenix.mine.model.AccountFullModel
import com.fhhy.phoenix.mine.model.ImageCheckCodeModel

// Created by admin on 2020/6/14.
class ImageCheckCodePresenter :
    BasePresenter<ImageCheckCodeContract.Model, ImageCheckCodeContract.View>(),
    ImageCheckCodeContract.Presenter {
    override fun createModel(): ImageCheckCodeContract.Model? = ImageCheckCodeModel()
}