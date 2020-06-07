package com.fhhy.phoenix.base

/**
 * Created by hesanwei on 2020/1/13.
 */
interface IView {

    /**
     * 显示加载
     */
    fun showLoading()

    /**
     * 隐藏加载
     */
    fun hideLoading()

    /**
     * 显示信息
     */
    fun showMsg(msg: String?)

    /**
     * 显示错误信息
     */
    fun showError(errorMsg: String?)

}