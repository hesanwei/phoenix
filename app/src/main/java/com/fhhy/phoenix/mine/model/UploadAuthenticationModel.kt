package com.fhhy.phoenix.mine.model

import android.util.Log
import com.fhhy.phoenix.base.BaseModel
import com.fhhy.phoenix.bean.AuthenticationBean
import com.fhhy.phoenix.bean.HttpResult
import com.fhhy.phoenix.bean.InviteRecordBean
import com.fhhy.phoenix.http.RetrofitManager
import com.fhhy.phoenix.mine.contract.UploadAuthenticationContract
import getRequestMap
import io.reactivex.Observable
import okhttp3.MultipartBody
import java.util.*

/**
 * Created by hecuncun on 2020/7/13
 */
class UploadAuthenticationModel : BaseModel(), UploadAuthenticationContract.Model {
    override fun requestAuthenticationIdCard(
        map: Map<String, String?>,
        partList: List<MultipartBody.Part>
    ): Observable<HttpResult<AuthenticationBean?>> {
        Log.e("hcc","Model请求接口")
        return RetrofitManager.apiService.requestAuthenticationIdCard(map, partList)
    }


}