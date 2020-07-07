package com.fhhy.phoenix.home.fragment

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.OnLifecycleEvent
import com.fhhy.phoenix.bean.CurrencyPrice
import com.fhhy.phoenix.http.RetrofitManager
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.BehaviorSubject
import io.reactivex.schedulers.Schedulers
import java.util.*
import kotlin.concurrent.timerTask
import kotlin.random.Random

class HomeLifeCycle : LifecycleObserver {

    private var isVisibleToUser: Boolean = false
    private var timer: Timer? = null
    private val mDataResource = BehaviorSubject.create<List<CurrencyPrice>>()
    private val mDisposable by lazy {
        CompositeDisposable()
    }

    val currenciesList:  Observable<List<CurrencyPrice>>
        get() = mDataResource.hide()

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart() {
        //init timer
        timer = Timer("MainLifeCycle")
    }
    
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume() {
        isVisibleToUser = true
        Log.d(TAG, "onResume thread : ${Thread.currentThread().name}")
        timer?.schedule(timerTask {
            if (isVisibleToUser) {
                updateData()
            }
        },0,1000 )
    }

    private fun updateData() {
        mDisposable.clear()
        val currenciesList = RetrofitManager.apiService.requestHomeCurrenciesSingle()
            .subscribeOn(Schedulers.io())
            .doOnSuccess {
                mDataResource.onNext(it.data.currencyPriceList)
             }
            .doOnError{}
            .subscribe({},{})

        mDisposable.add(currenciesList)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause() {
        isVisibleToUser = false
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStop() {
        mDisposable.clear()
        timer?.cancel()
        timer = null
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() {

    }

    fun onHideChanged(hidden: Boolean) {
        isVisibleToUser = !hidden
    }

    companion object {
         const val TAG = "HomeLifeCycle"
    }

}