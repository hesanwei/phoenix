package com.fhhy.phoenix.contractdetail

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.github.fujianlian.klinechart.DataHelper
import com.github.fujianlian.klinechart.KLineEntity
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import java.util.concurrent.TimeUnit

class ContractDetailViewModel(application: Application) : AndroidViewModel(application) {

    private val _kLineEntities = BehaviorSubject.create<List<KLineEntity>>()

    private val context: Context = application.applicationContext
    val kLineEntities: Observable<List<KLineEntity>>
        get() = _kLineEntities.hide()

    init {
        initDatas()
    }

    private fun initDatas() {
        Observable.create<List<KLineEntity>> {
            val datas = DataRequest.getALL(context).subList(0, 500)
            DataHelper.calculate(datas)
            it.onNext(datas)
        }   .delay(2000, TimeUnit.MILLISECONDS)
            .subscribeOn(Schedulers.io())
            .doOnNext {
                _kLineEntities.onNext(it)
            }.subscribe()
    }

    class Factory(val application: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return ContractDetailViewModel(application) as T
        }

    }

    companion object {
        const val TAG = "MainViewModel"
    }
}