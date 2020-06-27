package com.fhhy.phoenix.contractdetail.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import com.fhhy.phoenix.R

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
class ConfirmOrderDialog : FullScreenDialogFragment() {
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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val inflate = inflater.inflate(R.layout.dialog_confirm_order, null, false)
        val tvConfirm = inflate.findViewById<AppCompatTextView>(R.id.tv_confirm)
        val tvCancel = inflate.findViewById<AppCompatTextView>(R.id.tv_cancel)

        val tv_type = inflate.findViewById<AppCompatTextView>(R.id.tv_type)//类型
        val tv_lever = inflate.findViewById<AppCompatTextView>(R.id.tv_lever)//杠杆
        val tv_delegate_type = inflate.findViewById<AppCompatTextView>(R.id.tv_delegate_type)//委托类型
        val tv_capital = inflate.findViewById<AppCompatTextView>(R.id.tv_capital)//本金
        val tv_stop_profit_price =
            inflate.findViewById<AppCompatTextView>(R.id.tv_stop_profit_price)//止盈价
        val tv_stop_loss_price =
            inflate.findViewById<AppCompatTextView>(R.id.tv_stop_loss_price)//止损价
        tvCancel.setOnClickListener {
            dismiss()
        }
        tvConfirm.setOnClickListener {
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

        return inflate
    }

    private var onConfirmListener: OnConfirmListener? = null

    interface OnConfirmListener {
        fun onConfirm()
    }

    fun setOnConfirmListener(listener: OnConfirmListener): ConfirmOrderDialog {
        onConfirmListener = listener
        return this
    }
}