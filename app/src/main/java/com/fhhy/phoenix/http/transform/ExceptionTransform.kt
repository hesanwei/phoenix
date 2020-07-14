package com.fhhy.phoenix.http.transform

import com.fhhy.phoenix.http.ExceptionWrapper
import com.fhhy.phoenix.http.ExceptionWrapper.Companion.ERROR_MSG_NO_NETWORK
import com.fhhy.phoenix.http.ExceptionWrapper.Companion.ERROR_MSG_UNKNOWN
import io.reactivex.*
import retrofit2.HttpException
import java.io.IOException

object ExceptionTransform {

    private fun transformError(e: Throwable): ExceptionWrapper {
        try {
            return when (e) {
                is HttpException -> {
                    ExceptionWrapper(e.message ?: "", e.code())
                }
                is IOException -> {
                    ExceptionWrapper(
                        e.message ?: ERROR_MSG_NO_NETWORK,
                        ExceptionWrapper.ERROR_CODE_NO_NETWORK
                    )
                }
                else -> {
                    ExceptionWrapper(e.message ?: ERROR_MSG_UNKNOWN, ExceptionWrapper.ERROR_CODE_UNKNOWN)
                }
            }
        }catch (e: Throwable) {
            return ExceptionWrapper(e.message ?: ERROR_MSG_UNKNOWN, ExceptionWrapper.ERROR_CODE_UNKNOWN)
        }

    }

    fun <T> singleErrorTransform(): SingleTransformer<T, T> {
        return SingleTransformer {
            it.onErrorResumeNext { e ->
                Single.error(transformError(e))
            }
        }
    }

    fun <T> observableErrorTransform(): ObservableTransformer<T, T> {
        return ObservableTransformer {
            it.onErrorResumeNext { e: Throwable ->
                Observable.error(transformError(e))
            }
        }
    }

    fun <T> flowableErrorTransform(): FlowableTransformer<T, T> {
        return FlowableTransformer {
            it.onErrorResumeNext { e: Throwable ->
                Flowable.error(transformError(e))
            }
        }
    }

    fun completableErrorTransform(): CompletableTransformer {
        return CompletableTransformer {
            it.onErrorResumeNext { e: Throwable ->
                Completable.error(transformError(e))
            }
        }
    }
}