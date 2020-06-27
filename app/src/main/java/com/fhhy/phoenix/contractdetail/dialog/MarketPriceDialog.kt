package com.fhhy.phoenix.contractdetail.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import com.fhhy.phoenix.R

/**
 * 行情价格
 */
class MarketPriceDialog : FullScreenDialogFragment() {
    companion object {
        fun newInstance(): MarketPriceDialog {
            return MarketPriceDialog()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val inflate = inflater.inflate(R.layout.dialog_market_price, null, false)
        val tvConfirm = inflate.findViewById<AppCompatTextView>(R.id.tv_confirm)
        tvConfirm.setOnClickListener {
            dismiss()
        }

        return inflate
    }
}