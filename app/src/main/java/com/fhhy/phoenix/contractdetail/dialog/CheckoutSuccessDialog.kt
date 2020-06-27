package com.fhhy.phoenix.contractdetail.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import com.fhhy.phoenix.R

/**
 * 下单成功
 */
class CheckoutSuccessDialog : FullScreenDialogFragment() {
    companion object {
        fun newInstance(): CheckoutSuccessDialog {
            return CheckoutSuccessDialog()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val inflate = inflater.inflate(R.layout.dialog_checkout_success, null, false)
        val tvConfirm = inflate.findViewById<AppCompatTextView>(R.id.tv_confirm)
        tvConfirm.setOnClickListener {
            dismiss()
        }

        return inflate
    }
}