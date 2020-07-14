package com.fhhy.phoenix.mine.presenter

import android.util.Log
import com.fhhy.phoenix.base.BasePresenter
import com.fhhy.phoenix.mine.contract.UploadAuthenticationContract
import com.fhhy.phoenix.mine.contract.UploadPassportContract
import com.fhhy.phoenix.mine.model.UploadAuthenticationModel
import com.fhhy.phoenix.mine.model.UploadPassportModel
import okhttp3.MultipartBody
import request

/**
 * Created by hecuncun on 2020/7/13
 */
class UploadPassportPresenter:BasePresenter<UploadPassportContract.Model,UploadPassportContract.View>(),UploadPassportContract.Presenter {
    override fun createModel(): UploadPassportContract.Model? = UploadPassportModel()

    override fun requestAuthenticationIdCard(
        map: MutableMap<String, String?>,
        partList: List<MultipartBody.Part>) {
        mModel?.requestAuthenticationIdCard(map,partList)?.request(mModel,mView){
          mView?.requestAuthenticationIdCardSuccess(it.data)
      }
    }



}