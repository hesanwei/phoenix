package com.fhhy.phoenix.base

/**
 * Created by hesanwei on 2020/1/13.
 */
interface IPresenter<in V : IView> {

    /**
     * 绑定 View
     */
    fun attachView(mView: V)

    /**
     * 解绑 View
     */
    fun detachView()

}