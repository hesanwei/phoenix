package com.fhhy.phoenix.mine.contract

import com.fhhy.phoenix.base.IModel
import com.fhhy.phoenix.base.IPresenter
import com.fhhy.phoenix.base.IView
import com.fhhy.phoenix.bean.AuthenticationBean
import com.fhhy.phoenix.bean.HttpResult
import io.reactivex.Observable
import okhttp3.MultipartBody

/**
 * Created by hecuncun on 2020/7/13
 */
interface UploadPassportContract {
    interface View:IView{
        fun requestAuthenticationIdCardSuccess(authenticationBean: AuthenticationBean?)
    }
    interface Model:IModel{
        fun requestAuthenticationIdCard(map: Map<String,String?>,partList: List<MultipartBody.Part>): Observable<HttpResult<AuthenticationBean?>>
    }
    interface Presenter:IPresenter<View>{
        fun requestAuthenticationIdCard(map: MutableMap<String, String?>, partList: List<MultipartBody.Part>)
    }
}