package com.fhhy.phoenix.base

import io.reactivex.disposables.Disposable

/**
 * Created by hesanwei on 2020/1/13.
 */
interface IModel {

    fun addDisposable(disposable: Disposable?)

    fun onDetach()

}