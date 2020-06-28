package com.fhhy.phoenix.contractdetail.dialog

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import com.fhhy.phoenix.R
import com.fhhy.phoenix.dialog.BaseDialog
import kotlinx.android.synthetic.main.dialog_stop_profit_loss_des.*

/**
 * 止盈止损说明
 */
class StopProfitLossDesDialog : BaseDialog() {
    companion object {
        fun newInstance(): StopProfitLossDesDialog {
            return StopProfitLossDesDialog()
        }
    }

    override fun getLayoutId(): Int = R.layout.dialog_stop_profit_loss_des
    override fun initView() {
        tv_confirm.setOnClickListener {
            dismiss()
        }
    }

    override fun getGravity(): Int? = Gravity.CENTER

}