package com.fhhy.phoenix.mine.model

import com.fhhy.phoenix.base.BaseModel
import com.fhhy.phoenix.bean.HttpResult
import com.fhhy.phoenix.http.RetrofitManager
import com.fhhy.phoenix.mine.contract.UploadAuthenticationContract
import getRequestMap
import okhttp3.MultipartBody
import java.util.*

/**
 * Created by hecuncun on 2020/7/13
 */
class UploadAuthenticationModel : BaseModel(), UploadAuthenticationContract.Model {
    override fun requestAuthenticationIdCard(
        map: Map<String, String>,
        partList: List<MultipartBody.Part>
    ): Any? {
        val requestMap = getRequestMap()
        requestMap["idcard_name"] = map["name"]
        requestMap["idcard_number"] = map["number"]
        return RetrofitManager.apiService.requestAuthenticationIdCard(requestMap, partList)
    }

}