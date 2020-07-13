package com.fhhy.phoenix.http

import java.lang.Exception

class ExceptionWrapper(val errorMsg: String, val errorCode: Int) : Exception(errorMsg) {

    companion object {
        const val ERROR_CODE_UNKNOWN = -2020
        const val ERROR_CODE_NO_NETWORK = -2021
    }
}