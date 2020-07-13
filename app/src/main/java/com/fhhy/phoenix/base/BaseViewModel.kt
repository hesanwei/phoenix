package com.fhhy.phoenix.base

import androidx.lifecycle.ViewModel
import com.fhhy.phoenix.bean.HttpResult
import com.fhhy.phoenix.constants.Constants.RESPONSE_CODE_SUCCESS
import com.fhhy.phoenix.constants.Constants.RESPONSE_CODE_UNKNOWN
import com.fhhy.phoenix.http.ExceptionWrapper
import com.fhhy.phoenix.http.ExceptionWrapper.Companion.ERROR_CODE_UNKNOWN
import com.fhhy.phoenix.http.scheduler.SingleErrorConsumer
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel : ViewModel() {
    protected val mDisposables by lazy {
        CompositeDisposable()
    }

    protected fun <T : BaseBean> simpleRequest(
        api: () -> Single<T>,
        error: (e: ExceptionWrapper) -> Unit,
        success: (data: T) -> Unit,
        empty: (() -> Unit)? = null
    ) {
        val subscribe = api.invoke()
            .compose(SingleErrorConsumer())
            .doOnError {

            }
            .doOnSuccess {
                if (it.code?.toInt() == RESPONSE_CODE_SUCCESS) {
                    success.invoke(it)
                } else {
                    error.invoke(
                        ExceptionWrapper(
                            it.message ?: "Unknown Error",
                            it.code?.toInt() ?: ERROR_CODE_UNKNOWN
                        )
                    )
                }
            }
            .subscribe()
        mDisposables.add(subscribe)
    }

    override fun onCleared() {
        mDisposables.clear()
        super.onCleared()
    }
}