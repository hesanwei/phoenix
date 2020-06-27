package com.fhhy.phoenix.contractdetail.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import com.fhhy.phoenix.R

/**
 * 预估成交价
 */
class EstimateFinalPriceDialog : FullScreenDialogFragment() {
    companion object {
        fun newInstance(): EstimateFinalPriceDialog {
            return EstimateFinalPriceDialog()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val inflate = inflater.inflate(R.layout.dialog_estimate_final_price, null, false)
        val tvConfirm = inflate.findViewById<AppCompatTextView>(R.id.tv_confirm)
        tvConfirm.setOnClickListener {
            dismiss()
        }

        return inflate
    }
}