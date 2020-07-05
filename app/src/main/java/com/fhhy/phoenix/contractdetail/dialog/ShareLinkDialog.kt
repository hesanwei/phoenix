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
import kotlinx.android.synthetic.main.dialog_estimate_final_price.*
import kotlinx.android.synthetic.main.dialog_estimate_final_price.tv_confirm

/**
 * 分享给朋友
 * /**
 * Created by hecuncun on 2020/6/27
 * 下单确认  使用时设置 确认监听器
 * eg:
 *    ShareLinkDialog.newInstance()
                 .setOnConfirmListener(object :ShareLinkDialog.OnConfirmListener{
         override fun onConfirm() {
        showToast("确认")
         }
      })
.show(requireActivity().supportFragmentManager,"share")
*/
 */
class ShareLinkDialog : BaseDialog() {
    companion object {
        fun newInstance(): ShareLinkDialog {
            return ShareLinkDialog()
        }
    }

    override fun getLayoutId(): Int = R.layout.dialog_share_link


    private var onConfirmListener: OnConfirmListener? = null

    interface OnConfirmListener {
        fun onConfirm()
    }

    fun setOnConfirmListener(listener: OnConfirmListener): ShareLinkDialog {
        onConfirmListener = listener
        return this
    }

    override fun initView() {
        tv_cancel.setOnClickListener {
            dismiss()
        }
        tv_confirm.setOnClickListener {
            onConfirmListener?.onConfirm()
            dismiss()
        }
    }


    override fun getGravity(): Int? = Gravity.BOTTOM

}