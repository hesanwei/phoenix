package com.fhhy.phoenix.base

import android.util.Log
import androidx.lifecycle.ViewModel
import com.fhhy.phoenix.constants.Constants.RESPONSE_CODE_SUCCESS
import com.fhhy.phoenix.http.ExceptionWrapper
import com.fhhy.phoenix.http.ExceptionWrapper.Companion.ERROR_CODE_UNKNOWN
import com.fhhy.phoenix.http.transform.ExceptionTransform
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import resolve

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
            .subscribeOn(Schedulers.io())
            .compose(ExceptionTransform.singleErrorTransform())
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
            .doOnError {
                Log.d(TAG, "simpleRequest: ${it.message}")
                error.invoke(it.resolve())
            }
            .subscribe()
        mDisposables.add(subscribe)
    }

    override fun onCleared() {
        mDisposables.clear()
        super.onCleared()
    }

    companion object {
        const val TAG = "BaseViewModel"
    }
}