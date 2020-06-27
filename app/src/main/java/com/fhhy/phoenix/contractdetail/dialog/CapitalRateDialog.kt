package com.fhhy.phoenix.contractdetail.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import com.fhhy.phoenix.R

/**
 * Created by hecuncun on 2020/6/27
 * 资金费率
 */
class CapitalRateDialog : FullScreenDialogFragment() {
    companion object {
        fun newInstance(): CapitalRateDialog {
            return CapitalRateDialog()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val inflate = inflater.inflate(R.layout.dialog_capital_rate, null, false)
        val tvConfirm = inflate.findViewById<AppCompatTextView>(R.id.tv_confirm)
        tvConfirm.setOnClickListener {
            dismiss()
        }

        return inflate
    }
}