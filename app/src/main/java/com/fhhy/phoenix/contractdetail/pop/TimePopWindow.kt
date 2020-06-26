package com.fhhy.phoenix.contractdetail.pop

import android.content.Context
import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import com.fhhy.phoenix.R
import io.reactivex.rxjava3.disposables.CompositeDisposable
import noDoubleClick
import razerdp.basepopup.BasePopupWindow


class TimePopWindow(context: Context) : BasePopupWindow(context) {
    private var mOnSelectedPeriodListener: OnSelectedPeriodListener? = null

    private lateinit var mCompositeDisposable: CompositeDisposable

    override fun onCreateContentView(): View {
        mCompositeDisposable = CompositeDisposable()
        return createPopupById(R.layout.pop_time_layout)
    }

    fun setOnSelectedPeriodListener(onSelectedPeriodListener: OnSelectedPeriodListener) {
        mOnSelectedPeriodListener = onSelectedPeriodListener
    }

    override fun onViewCreated(contentView: View?) {
        contentView?.apply {
            val hourOne = findViewById<View>(R.id.hour_one).noDoubleClick {
                mOnSelectedPeriodListener?.onSelectedPeriod(1)
                dismiss()
            }
            mCompositeDisposable.add(hourOne)
            val hourFour = findViewById<View>(R.id.hour_four).noDoubleClick {
                mOnSelectedPeriodListener?.onSelectedPeriod(4)
                dismiss()
            }
            mCompositeDisposable.add(hourFour)
            val dayOne = findViewById<View>(R.id.day_one).noDoubleClick {
                mOnSelectedPeriodListener?.onSelectedPeriod(24)
                dismiss()
            }
            mCompositeDisposable.add(dayOne)
        }

    }

    override fun onDestroy() {
        mCompositeDisposable.clear()
        super.onDestroy()
    }

    override fun onCreateShowAnimation(width: Int, height: Int): Animation {
        val showAnimation: Animation = AlphaAnimation(0f, 1f)
        showAnimation.duration = 400
        return showAnimation
    }

    override fun onCreateDismissAnimation(): Animation {
        val dismissAnim: Animation = AlphaAnimation(1f, 0f)
        dismissAnim.duration = 400
        return dismissAnim
    }

    interface OnSelectedPeriodListener {
        fun onSelectedPeriod(period: Int)
    }
}