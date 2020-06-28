package com.fhhy.phoenix.contractdetail.dialog

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import com.fhhy.phoenix.R
import com.fhhy.phoenix.dialog.BaseDialog
import kotlinx.android.synthetic.main.dialog_plan_delegate_des.*

/**
 * 计划委托说明
 */
class PlanDelegateDesDialog : BaseDialog() {
    companion object {
        fun newInstance(): PlanDelegateDesDialog {
            return PlanDelegateDesDialog()
        }
    }

    override fun getLayoutId(): Int = R.layout.dialog_plan_delegate_des

    override fun initView() {
        tv_confirm.setOnClickListener {
            dismiss()
        }
    }

    override fun getGravity(): Int? = Gravity.CENTER

}