package com.fhhy.phoenix.base

import android.graphics.Color
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.fhhy.phoenix.R
import com.fhhy.phoenix.event.EmptyEvent
import com.jaeger.library.StatusBarUtil
import me.jessyan.autosize.internal.CustomAdapt
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/**
 * BaseActivity class
 */
abstract class BaseActivity : AppCompatActivity(), CustomAdapt {
    /**
     * 是否使用 EventBus
     */
    open fun useEventBus(): Boolean = true

    /**
     * 是否设置状态栏颜色为白色
     */
    open fun setStatusBar(): Boolean = true

    /**
     * 布局文件id
     */
    abstract fun getLayoutId(): Int


    /**
     * 初始化 View
     */
    abstract fun initView()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN or WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)//软键盘不弹出，在setContentView()之前执行
        window.decorView.setBackgroundColor(resources.getColor(R.color.global_bg))
        setContentView(getLayoutId())
        if (useEventBus()) {
            EventBus.getDefault().register(this)
        }
        if (setStatusBar()) {
            StatusBarUtil.setColor(this, Color.WHITE, 0)
            StatusBarUtil.setLightMode(this)
        }
        initView()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (useEventBus()) {
            EventBus.getDefault().unregister(this)
        }
    }

    override fun isBaseOnWidth(): Boolean = true

    override fun getSizeInDp(): Float = 360f

    @Subscribe(threadMode = ThreadMode.MAIN)
    open fun onReceiveEvent(event: EmptyEvent) {

    }
}