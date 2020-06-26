package com.fhhy.phoenix.contractdetail.pop

import android.content.Context
import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import com.fhhy.phoenix.R
import razerdp.basepopup.BasePopupWindow


class QuotaPopWindow(context: Context) : BasePopupWindow(context) {

    override fun onCreateContentView(): View {
        return createPopupById(R.layout.pop_quota_layout)
    }

    override fun onViewCreated(contentView: View?) {

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


}