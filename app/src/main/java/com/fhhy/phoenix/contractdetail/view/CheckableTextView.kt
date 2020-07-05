package com.fhhy.phoenix.contractdetail.view

import android.R
import android.content.Context
import android.util.AttributeSet
import android.view.accessibility.AccessibilityEvent
import android.widget.Checkable
import androidx.appcompat.widget.AppCompatTextView

class CheckableTextView constructor(context: Context, attributeSet: AttributeSet) : AppCompatTextView(context, attributeSet), Checkable {
    var mChecked = false
    //选中状态对应的系统资源
    private val CHECKED_STATE_SET = intArrayOf(
        R.attr.state_checked
    )

    override fun onCreateDrawableState(extraSpace: Int): IntArray {
        //固定写法
         val drawableState = super.onCreateDrawableState(extraSpace + 1);
        //判断是否选择
        if (isChecked) {
            //如果选择, 把选择的状态, 合并到现有的状态中.
            mergeDrawableStates(drawableState, CHECKED_STATE_SET);
        }
        return drawableState;
    }

    override fun isChecked(): Boolean = mChecked

    override fun toggle() {
        isChecked = !mChecked
    }

    override fun setChecked(checked: Boolean) {
        if (mChecked != checked) {
            mChecked = checked
            refreshDrawableState()
        }
    }
}