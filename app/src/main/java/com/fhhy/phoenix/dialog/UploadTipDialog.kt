package com.fhhy.phoenix.dialog

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import com.fhhy.phoenix.R
import com.fhhy.phoenix.dialog.BaseDialog
import kotlinx.android.synthetic.main.dialog_stop_profit_loss_des.*

/**
 * 上传示例
 */
class UploadTipDialog : BaseDialog() {
    companion object {
        fun newInstance(): UploadTipDialog {
            return UploadTipDialog()
        }
    }

    override fun getLayoutId(): Int = R.layout.dialog_upload_tip
    override fun initView() {
        tv_confirm.setOnClickListener {
            dismiss()
        }
    }

    override fun getGravity(): Int? = Gravity.CENTER

}