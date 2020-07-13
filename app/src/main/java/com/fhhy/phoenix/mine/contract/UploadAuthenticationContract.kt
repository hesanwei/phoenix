package com.fhhy.phoenix.mine.contract

import com.fhhy.phoenix.base.IModel
import com.fhhy.phoenix.base.IPresenter
import com.fhhy.phoenix.base.IView
import okhttp3.MultipartBody

/**
 * Created by hecuncun on 2020/7/13
 */
interface UploadAuthenticationContract {
    interface View:IView{
        fun requestAuthenticationIdCardSuccess(bean:Any?)
    }
    interface Model:IModel{
        fun requestAuthenticationIdCard(map: Map<String,String>,partList: List<MultipartBody.Part>):Any?
    }
    interface Presenter:IPresenter<View>{
        fun requestAuthenticationIdCard(map: Map<String,String>,partList: List<MultipartBody.Part>)
    }
}