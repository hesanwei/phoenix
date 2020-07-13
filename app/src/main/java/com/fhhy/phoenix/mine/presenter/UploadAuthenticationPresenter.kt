package com.fhhy.phoenix.mine.presenter

import com.fhhy.phoenix.base.BasePresenter
import com.fhhy.phoenix.mine.contract.UploadAuthenticationContract
import com.fhhy.phoenix.mine.model.UploadAuthenticationModel
import getRequestMap
import okhttp3.MultipartBody
import okhttp3.RequestBody

/**
 * Created by hecuncun on 2020/7/13
 */
class UploadAuthenticationPresenter:BasePresenter<UploadAuthenticationContract.Model,UploadAuthenticationContract.View>(),UploadAuthenticationContract.Presenter {
    override fun createModel(): UploadAuthenticationContract.Model? =UploadAuthenticationModel()

    override fun requestAuthenticationIdCard(map: Map<String,String>,partList: List<MultipartBody.Part>) {
      mModel?.requestAuthenticationIdCard(map,partList)?.getRequestMap()
    }



}