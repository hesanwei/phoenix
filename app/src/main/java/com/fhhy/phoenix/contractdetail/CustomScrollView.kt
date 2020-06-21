package com.fhhy.phoenix.contractdetail

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.core.widget.NestedScrollView

class CustomScrollView constructor(context: Context, attributeSet: AttributeSet) : NestedScrollView(context, attributeSet) {
    private var isOldEvent = false
    private var downX = 0f
    private var downY = 0f

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        if (isOldEvent) {
            return false
        }
        when (ev.action) {
            MotionEvent.ACTION_DOWN -> {
                downX = ev.x
                downY = ev.y
            }
            MotionEvent.ACTION_MOVE -> {
                val moveX = ev.x
                val moveY = ev.y
                val difX = Math.abs(moveX - downX)
                val difY = Math.abs(moveY - downY)
                if (difX > difY) {
                    downX = moveX
                    downY = moveY
                    isOldEvent = true
                    return false
                }
                downX = moveX
                downY = moveY
            }
            MotionEvent.ACTION_UP -> {
            }
        }
        return super.onInterceptTouchEvent(ev)
    }

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        when (ev.action) {
            MotionEvent.ACTION_UP -> isOldEvent = false
        }
        return super.dispatchTouchEvent(ev)
    }

}