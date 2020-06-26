package com.fhhy.phoenix.contractdetail

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import com.donkingliang.consecutivescroller.ConsecutiveViewPager

class NoScrollViewPager(context: Context, attributeSet: AttributeSet) : ConsecutiveViewPager(context, attributeSet) {

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        return false
    }

    override fun onTouchEvent(ev: MotionEvent?): Boolean {
        return false
    }
}