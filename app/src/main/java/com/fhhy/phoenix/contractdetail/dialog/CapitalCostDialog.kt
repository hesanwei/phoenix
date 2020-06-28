package com.fhhy.phoenix.contractdetail.dialog

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import com.fhhy.phoenix.R
import com.fhhy.phoenix.dialog.BaseDialog
import kotlinx.android.synthetic.main.dialog_capital_cost.*

/**
 * 资金费用
 */
class CapitalCostDialog : BaseDialog() {

    override fun getLayoutId(): Int = R.layout.dialog_capital_cost

    override fun initView() {
        tv_confirm.setOnClickListener {
            dismiss()
        }

        if (arguments != null) {
            val estimateRate = arguments!!.getString("estimateRate")
            val nextEstimateCost = arguments!!.getString("nextEstimateCost")
            tv_estimate_cost_rate.text =
                String.format(resources.getString(R.string.estimate_capital_rate), estimateRate)
            tv_next_estimate_cost.text =
                String.format(resources.getString(R.string.next_capital_cost), nextEstimateCost)
        }
    }

    override fun getGravity(): Int? = Gravity.CENTER

    companion object {
        fun newInstance(estimateRate: String, nextEstimateCost: String): CapitalCostDialog {
            val dialog = CapitalCostDialog()
            val bundle = Bundle()
            bundle.putString("estimateRate", estimateRate)
            bundle.putString("nextEstimateCost", nextEstimateCost)
            dialog.arguments = bundle
            return dialog
        }
    }

}