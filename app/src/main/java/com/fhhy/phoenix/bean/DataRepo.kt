package com.fhhy.phoenix.bean

import com.fhhy.phoenix.base.BaseBean

/**
 * DataDepo class
 *
 * @author admin created on 2020/7/5
 *
 */
data class HttpResult<T>(
    val data: T
) : BaseBean()

