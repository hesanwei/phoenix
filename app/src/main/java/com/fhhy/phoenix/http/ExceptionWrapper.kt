package com.fhhy.phoenix.http

import java.lang.Exception

class ExceptionWrapper(val errorMsg: String, val errorCode: Int) : Exception(errorMsg) {

    companion object {
        const val ERROR_CODE_UNKNOWN = -2
        const val ERROR_CODE_NO_NETWORK = -1
        const val ERROR_MSG_UNKNOWN = "Unknown Error"
        const val ERROR_MSG_NO_NETWORK = "No Network, Please retry!"
    }
}