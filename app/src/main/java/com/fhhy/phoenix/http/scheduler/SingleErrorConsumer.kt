package com.fhhy.phoenix.http.scheduler

import com.fhhy.phoenix.http.ExceptionWrapper
import io.reactivex.Single
import io.reactivex.SingleSource
import io.reactivex.SingleTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException
import java.io.IOException

fun Any.resolveError(e: Throwable): ExceptionWrapper {
    return when (e) {
        is HttpException -> {
            ExceptionWrapper(e.message ?: "", e.code())
        }
        is IOException -> {
            ExceptionWrapper(e.message ?: "", ExceptionWrapper.ERROR_CODE_NO_NETWORK)
        }
        else -> {
            ExceptionWrapper(e.message ?: "", ExceptionWrapper.ERROR_CODE_UNKNOWN)
        }
    }
}

class SingleErrorConsumer<T> : SingleTransformer<T, T> {
    override fun apply(upstream: Single<T>): SingleSource<T> {
        return upstream.onErrorResumeNext {error->
            //TODO 捕捉异常消费
            SingleSource {
                it.onError(resolveError(error))
            }
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}