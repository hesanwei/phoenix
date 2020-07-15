package com.fhhy.phoenix.mine.presenter

import com.fhhy.phoenix.base.BasePresenter
import com.fhhy.phoenix.mine.contract.ImageCheckCodeContract
import com.fhhy.phoenix.mine.model.ImageCheckCodeModel
import request

// Created by admin on 2020/6/14.
class ImageCheckCodePresenter :
    BasePresenter<ImageCheckCodeContract.Model, ImageCheckCodeContract.View>(),
    ImageCheckCodeContract.Presenter {
    override fun createModel(): ImageCheckCodeContract.Model? = ImageCheckCodeModel()
    override fun requestUpdatePwdCheckCode(mobile: String, imgCheckCode: String) {
       mModel?.requestUpdatePwdCheckCode(mobile,imgCheckCode)?.request(mModel,mView){
           mView?.requestUpdatePwdCheckCodeSuccess()
       }
    }

    override fun requestUpdateCostPwdCheckCode(imgCheckCode: String) {
        mModel?.requestUpdateCostPwdCheckCode(imgCheckCode)?.request(mModel,mView){
            mView?.requestUpdateCostPwdCheckCodeSuccess()
        }
    }
}