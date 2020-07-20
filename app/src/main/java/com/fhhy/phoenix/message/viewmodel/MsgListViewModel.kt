package com.fhhy.phoenix.message.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.fhhy.phoenix.base.BaseViewModel
import com.fhhy.phoenix.bean.MsgItem
import com.fhhy.phoenix.bean.ResultData
import com.fhhy.phoenix.http.RetrofitManager
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject

class MsgListViewModel(private val msgType: Int) : BaseViewModel() {
    private val _msgList = BehaviorSubject.create<ResultData<List<MsgItem>>>()
    private val _consumeMsgSuccess = BehaviorSubject.create<Int>()

    val msgList: Observable<ResultData<List<MsgItem>>>
        get() = _msgList.hide()

    val consumeMsgSuccess: Observable<Int>
        get() = _consumeMsgSuccess.hide()

    private var mCurPage: Int = 1
    private var mTotalPage: Int = 0

    init {
        requestMsgList()
    }

    private fun requestMsgList() {
        _msgList.onNext(ResultData.loading())
        simpleRequest(api = {
            RetrofitManager.apiService.requestMsgList(
                mapOf(
                    "type" to "$msgType",
                    "page" to "$mCurPage"
                )
            )
        }, error = { e ->
            _msgList.onNext(ResultData.error(e.errorMsg, e.errorCode))
        }, success = {
            val data = it.data
            mCurPage = data.currentPage
            mTotalPage = data.lastPage
            if (data.msgList.isEmpty()) {
                _msgList.onNext(ResultData.empty())
            } else {
                _msgList.onNext(ResultData.success(data = data.msgList))
            }
            if (mCurPage >= mTotalPage) {
                _msgList.onNext(ResultData.complete(null))
            }
        })
    }

    fun requestMorePage() {
        mCurPage++;
        simpleRequest(api = {
            RetrofitManager.apiService.requestMsgList(
                mapOf(
                    "type" to "$msgType",
                    "page" to "$mCurPage"
                )
            )
        }, error = { e ->
            _msgList.onNext(ResultData.error(e.errorMsg, e.errorCode))
        }, success = {
            val data = it.data
            mCurPage = data.currentPage
            mTotalPage = data.lastPage
            _msgList.onNext(ResultData.success(data = data.msgList))
            if (mCurPage >= mTotalPage || data.msgList.isEmpty()) {
                _msgList.onNext(ResultData.complete(null))
            }
        })
    }

    fun consumeMsgRead(id: Int) {
        simpleRequest(api = {
            RetrofitManager.apiService.consumeMsgRead(
                mapOf(
                    "id" to "$id"
                )
            )
        }, error = {}, success = {
            _consumeMsgSuccess.onNext(0)
        })
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