package com.fhhy.phoenix.mine.presenter

import android.util.Log
import com.fhhy.phoenix.base.BasePresenter
import com.fhhy.phoenix.mine.contract.UploadAuthenticationContract
import com.fhhy.phoenix.mine.model.UploadAuthenticationModel
import okhttp3.MultipartBody
import request

/**
 * Created by hecuncun on 2020/7/13
 */
class UploadAuthenticationPresenter:BasePresenter<UploadAuthenticationContract.Model,UploadAuthenticationContract.View>(),UploadAuthenticationContract.Presenter {
    override fun createModel(): UploadAuthenticationContract.Model? = UploadAuthenticationModel()

    override fun requestAuthenticationIdCard(
        map: MutableMap<String, String?>,
        partList: List<MultipartBody.Part>) {
        Log.e("hcc","Presenter请求接口")
        mModel?.requestAuthenticationIdCard(map,partList)?.request(mModel,mView){
          mView?.requestAuthenticationIdCardSuccess(it.data)
      }
    }



}