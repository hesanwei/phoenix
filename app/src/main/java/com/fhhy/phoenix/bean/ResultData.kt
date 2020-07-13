package com.fhhy.phoenix.bean

import com.fhhy.phoenix.constants.Constants.RESPONSE_CODE_UNKNOWN


data class ResultData<out T>(
    val data: T?,
    val status: RequestStatus,
    val errorMsg: String = "Unknown Error",
    val errorCode: Int = RESPONSE_CODE_UNKNOWN
) {
    companion object {

        fun <T> loading(): ResultData<T> = ResultData(null, RequestStatus.LOADING)

        fun <T> success(data : T?) : ResultData<T> = ResultData(data, RequestStatus.SUCCESS)

        fun <T> empty() : ResultData<T> = ResultData(null, RequestStatus.EMPTY)

        fun <T> complete(data: T?) : ResultData<T> = ResultData(data, RequestStatus.COMPLETE)

        fun <T> error(errorMsg: String, errorCode: Int): ResultData<T> = ResultData(null, RequestStatus.ERROR, errorMsg, errorCode)

    }
}