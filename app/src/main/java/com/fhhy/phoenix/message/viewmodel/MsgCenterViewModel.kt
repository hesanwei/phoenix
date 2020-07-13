package com.fhhy.phoenix.message.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.fhhy.phoenix.base.BaseViewModel
import com.fhhy.phoenix.bean.MsgCenterBean
import com.fhhy.phoenix.bean.ResultData
import com.fhhy.phoenix.http.RetrofitManager
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject

class MsgCenterViewModel : BaseViewModel() {
    private val _msgCenterBeans = BehaviorSubject.create<ResultData<List<MsgCenterBean>>>()

    val msgCenterBeen: Observable<ResultData<List<MsgCenterBean>>>
        get() = _msgCenterBeans.hide()

    init {
        requestMsgCenterItems()
    }

    private fun requestMsgCenterItems() {
        _msgCenterBeans.onNext(ResultData.loading())
        simpleRequest(api = {
            RetrofitManager.apiService.requestMsgCenterList()
        }, error = { e ->
            _msgCenterBeans.onNext(ResultData.error(e.errorMsg, e.errorCode))
        }, success = {
            _msgCenterBeans.onNext(ResultData.success(it.data))
        })
    }

    internal class Factory() : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MsgCenterViewModel::class.java)) {
                return MsgCenterViewModel() as T
            }
            throw ClassCastException("not MsgCenterViewModel")
        }
    }
}