package com.fhhy.phoenix.mine.model

import com.fhhy.phoenix.base.BaseModel
import com.fhhy.phoenix.bean.HttpResult
import com.fhhy.phoenix.bean.LevelBean
import com.fhhy.phoenix.http.RetrofitManager
import com.fhhy.phoenix.mine.contract.MyLevelContract
import io.reactivex.Observable

// Created by admin on 2020/7/12.
class MyLevelModel : BaseModel(), MyLevelContract.Model {
    override fun requestMyLevel(): Observable<HttpResult<LevelBean?>> {
        return RetrofitManager.apiService.requestMyLevel()
    }

}