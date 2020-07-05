package com.fhhy.phoenix.widgets

import android.annotation.SuppressLint
import android.content.Context
import android.text.TextUtils
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import com.fhhy.phoenix.R
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import java.util.concurrent.TimeUnit

class CountdownTextView : AppCompatTextView {

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attributeSet: AttributeSet?) : this(context, attributeSet, 0)
    constructor(context: Context, attributeSet: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attributeSet,
        defStyleAttr
    ) {
        with(context.obtainStyledAttributes(attributeSet, R.styleable.CountdownTextView)) {
            count = getInteger(R.styleable.CountdownTextView_ctvDefaultCount, 59)
            val string = getString(R.styleable.CountdownTextView_ctvStopText)
            if (string != null && !TextUtils.isEmpty(string)) {
                stopText = string
            }
            stopTextColor = getColor(
                R.styleable.CountdownTextView_ctvStopTextColor,
                context.resources.getColor(R.color.text_main_blue)
            )
            countTextColor = getColor(
                R.styleable.CountdownTextView_ctvCountTextColor,
                context.resources.getColor(R.color.text_main_gray)
            )
        }
    }

    private var count: Int
    private var stopText: String = "获取验证码"
    private var stopTextColor: Int
    private var countTextColor: Int
    private var subscribe: Disposable? = null//保存订阅者

    @SuppressLint("SetTextI18n")
    fun start() {
        isClickable = false//禁用点击,防止重复操作
        text = "${count + 1}S"
        subscribe = Observable.interval(1, TimeUnit.SECONDS)//按时间间隔发送整数的Observable
            .observeOn(AndroidSchedulers.mainThread())//切换到主线程修改UI
            .subscribe {
                val show = count - it
                if (show < 0.toLong()) {//当倒计时小于0,计时结束
                    stop()
                    return@subscribe//使用标记跳出方法
                }
                text = "${show}S"
                setTextColor(countTextColor)
            }
    }

    /**
     * 结束计时,重新开始
     */
    fun stop() {
        if (subscribe != null) {
            subscribe?.dispose()//取消订阅
            text = stopText
            setTextColor(stopTextColor)
            isClickable = true//重新开启点击事件
            subscribe = null
        }
    }

}