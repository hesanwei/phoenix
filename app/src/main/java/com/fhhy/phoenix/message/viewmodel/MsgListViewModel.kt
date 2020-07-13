package com.fhhy.phoenix.message.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.fhhy.phoenix.base.BaseViewModel
import com.fhhy.phoenix.bean.MsgItem
import com.fhhy.phoenix.bean.ResultData
import com.fhhy.phoenix.http.RetrofitManager
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject

class MsgListViewModel(private val msgType: Int) : BaseViewModel() {
    private val _msgList = BehaviorSubject.create<ResultData<List<MsgItem>>>()

    val msgList: Observable<ResultData<List<MsgItem>>>
        get() = _msgList.hide()

    private var mCurPage: Int = 0
    private var mTotalPage: Int = 0
    init {
        requestMsgList()
    }

    private fun requestMsgList() {
        _msgList.onNext(ResultData.loading())
        simpleRequest(api = {
            RetrofitManager.apiService.requestMsgList(mapOf("type" to  "$msgType"))
        }, error = { e ->
            _msgList.onNext(ResultData.error(e.errorMsg, e.errorCode))
        }, success = {
            val data = it.data
            mCurPage = data.currentPage
            mTotalPage = data.lastPage
            if (mCurPage == mTotalPage) {
                _msgList.onNext(ResultData.success(data = data.msgList))
                _msgList.onComplete()
            }else {
                _msgList.onNext(ResultData.success(data = data.msgList))
            }
        })
    }

    fun requestMorePage() {
        //TODO  分页查询需要传入当前page

    }

    internal class Factory(val msgType: Int) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MsgListViewModel::class.java)) {
                return MsgListViewModel(msgType) as T
            }
            throw ClassCastException("not MsgListViewModel")
        }
    }
}