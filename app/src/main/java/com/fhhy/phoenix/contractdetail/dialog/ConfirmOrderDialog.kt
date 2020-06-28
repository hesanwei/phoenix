package com.fhhy.phoenix.contractdetail.dialog

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import com.fhhy.phoenix.R
import com.fhhy.phoenix.dialog.BaseDialog
import kotlinx.android.synthetic.main.dialog_confirm_order.*

/**
 * Created by hecuncun on 2020/6/27
 * 下单确认  使用时设置 确认监听器
 * eg:
 *    ConfirmOrderDialog.newInstance("做多","2X","市价","100VST","20","10")
      .setOnConfirmListener(object :ConfirmOrderDialog.OnConfirmListener{
                override fun onConfirm() {
                  showToast("确认")
                 }
           })
     .show(requireActivity().supportFragmentManager,"confirmOrder")
 */
class ConfirmOrderDialog : BaseDialog() {
    companion object {
        fun newInstance(
            type: String,
            lever: String,
            delegateType: String,
            capital: String,
            profitPrice: String,
            lossPrice: String
        ): ConfirmOrderDialog {
            val dialog = ConfirmOrderDialog()
            val bundle = Bundle()
            bundle.putString("type", type)
            bundle.putString("lever", lever)
            bundle.putString("delegateType", delegateType)
            bundle.putString("capital", capital)
            bundle.putString("profitPrice", profitPrice)
            bundle.putString("lossPrice", lossPrice)
            dialog.arguments = bundle
            return dialog
        }
    }

    override fun getLayoutId(): Int = R.layout.dialog_confirm_order

    override fun initView() {
        tv_cancel.setOnClickListener {
            dismiss()
        }
        tv_confirm.setOnClickListener {
            onConfirmListener?.onConfirm()
            dismiss()
        }
        if (arguments != null) {
            tv_type.text = arguments!!.getString("type")
            tv_lever.text = arguments!!.getString("lever")
            tv_delegate_type.text = arguments!!.getString("delegateType")
            tv_capital.text = arguments!!.getString("capital")
            tv_stop_profit_price.text = arguments!!.getString("profitPrice")
            tv_stop_loss_price.text = arguments!!.getString("lossPrice")
        }
    }
    override fun getGravity(): Int? = Gravity.CENTER

    private var onConfirmListener: OnConfirmListener? = null

    interface OnConfirmListener {
        fun onConfirm()
    }

    fun setOnConfirmListener(listener: OnConfirmListener): ConfirmOrderDialog {
        onConfirmListener = listener
        return this
    }
}