package com.fhhy.phoenix.mine.model

import com.fhhy.phoenix.base.BaseModel
import com.fhhy.phoenix.bean.HttpResult
import com.fhhy.phoenix.bean.InviteRecordBean
import com.fhhy.phoenix.http.RetrofitManager
import com.fhhy.phoenix.mine.contract.InviteRecordContract
import getRequestMap
import io.reactivex.Observable

// Created by admin on 2020/7/12.
class InviteRecordModel : BaseModel(), InviteRecordContract.Model {
    override fun requestInviteRecord(page: Int): Observable<HttpResult<InviteRecordBean?>> {
        val requestMap = getRequestMap()
        requestMap["page"] = page.toString()
        return RetrofitManager.apiService.requestInviteRecord(requestMap)
    }

}