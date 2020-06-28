package com.fhhy.phoenix.contractdetail.dialog

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import com.fhhy.phoenix.R
import com.fhhy.phoenix.dialog.BaseDialog
import kotlinx.android.synthetic.main.dialog_checkout_success.*

/**
 * 下单成功
 */
class CheckoutSuccessDialog : BaseDialog() {

    override fun getLayoutId(): Int = R.layout.dialog_checkout_success

    override fun initView() {
        tv_confirm.setOnClickListener {
            dismiss()
        }
    }

    override fun getGravity(): Int? = Gravity.CENTER

    companion object {
        fun newInstance(): CheckoutSuccessDialog {
            return CheckoutSuccessDialog()
        }
    }
}