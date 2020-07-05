package com.fhhy.phoenix.contractdetail.dialog

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import com.fhhy.phoenix.R
import com.fhhy.phoenix.dialog.BaseDialog
import kotlinx.android.synthetic.main.dialog_estimate_final_price.*

/**
 * 实际跟单总收益说明
 */
class RealFollowOrderProfitDesDialog : BaseDialog() {
    companion object {
        fun newInstance(): RealFollowOrderProfitDesDialog {
            return RealFollowOrderProfitDesDialog()
        }
    }

    override fun getLayoutId(): Int = R.layout.dialog_real_follow_order_profit_des

    override fun initView() {
        tv_confirm.setOnClickListener {
            dismiss()
        }
    }

    override fun getGravity(): Int? = Gravity.CENTER

}