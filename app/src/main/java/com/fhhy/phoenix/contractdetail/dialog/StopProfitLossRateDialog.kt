package com.fhhy.phoenix.contractdetail.dialog


import android.view.Gravity
import android.view.View
import com.fhhy.phoenix.R
import com.fhhy.phoenix.dialog.BaseDialog
import kotlinx.android.synthetic.main.dialog_confirm_order.*
import kotlinx.android.synthetic.main.dialog_confirm_order.tv_cancel
import kotlinx.android.synthetic.main.dialog_stop_profit_loss_des.tv_confirm
import kotlinx.android.synthetic.main.dialog_stop_profit_loss_rate.*

/**
 * 止盈止损比例设置
 */
class StopProfitLossRateDialog : BaseDialog() {
    private var profitOpen =true
    private var lossOpen =true
    companion object {
        fun newInstance(): StopProfitLossRateDialog {
            return StopProfitLossRateDialog()
        }
    }

    override fun getLayoutId(): Int = R.layout.dialog_stop_profit_loss_rate
    override fun initView() {
        tv_cancel.setOnClickListener {
            dismiss()
        }
        tv_confirm.setOnClickListener {
            onConfirmListener?.onConfirm()
            dismiss()
        }
        iv_stop_profit.setOnClickListener {
            profitOpen=!profitOpen
            hideOrShowProfitContainer(profitOpen)
        }

        iv_stop_loss.setOnClickListener {
            lossOpen=!lossOpen
            hideOrShowLossContainer(lossOpen)
        }
    }

    private fun hideOrShowLossContainer(lossOpen: Boolean) {
        if (lossOpen){
            iv_stop_loss.setImageResource(R.mipmap.icon_switch_on)
            container_stop_loss.visibility= View.VISIBLE
            leverage_container_rg_loss.visibility=View.VISIBLE
        }else{
            iv_stop_loss.setImageResource(R.mipmap.icon_switch_off)
            container_stop_loss.visibility= View.GONE
            leverage_container_rg_loss.visibility=View.GONE
        }
    }

    //布局开关
    private fun hideOrShowProfitContainer(profitOpen: Boolean) {
        if (profitOpen){
            iv_stop_profit.setImageResource(R.mipmap.icon_switch_on)
            container_stop_profit.visibility= View.VISIBLE
            leverage_container_rg_profit.visibility=View.VISIBLE
        }else{
            iv_stop_profit.setImageResource(R.mipmap.icon_switch_off)
            container_stop_profit.visibility= View.GONE
            leverage_container_rg_profit.visibility=View.GONE
        }
    }

    override fun getGravity(): Int? = Gravity.CENTER


    private var onConfirmListener: OnConfirmListener? = null

    interface OnConfirmListener {
        fun onConfirm()
    }

    fun setOnConfirmListener(listener: OnConfirmListener): StopProfitLossRateDialog {
        onConfirmListener = listener
        return this
    }
}