package com.fhhy.phoenix.contractdetail.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import com.fhhy.phoenix.R

/**
 * 资金费用
 */
class CapitalCostDialog : FullScreenDialogFragment() {
    companion object {
        fun newInstance(estimateRate:String,nextEstimateCost:String): CapitalCostDialog {
            val dialog =CapitalCostDialog()
            val bundle =Bundle()
            bundle.putString("estimateRate",estimateRate)
            bundle.putString("nextEstimateCost",nextEstimateCost)
            dialog.arguments=bundle
            return dialog
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val inflate = inflater.inflate(R.layout.dialog_capital_cost, null, false)
        val tvConfirm = inflate.findViewById<AppCompatTextView>(R.id.tv_confirm)
        val tv_estimate_cost_rate = inflate.findViewById<AppCompatTextView>(R.id.tv_estimate_cost_rate)
        val tv_next_estimate_cost = inflate.findViewById<AppCompatTextView>(R.id.tv_next_estimate_cost)
        tvConfirm.setOnClickListener {
            dismiss()
        }

        if (arguments!=null){
           val estimateRate = arguments!!.getString("estimateRate")
           val nextEstimateCost = arguments!!.getString("nextEstimateCost")
            tv_estimate_cost_rate.text=String.format(resources.getString(R.string.estimate_capital_rate), estimateRate)
            tv_next_estimate_cost.text=String.format(resources.getString(R.string.next_capital_cost), nextEstimateCost)
        }

        return inflate
    }
}