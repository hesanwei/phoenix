package com.fhhy.phoenix.message.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.fhhy.phoenix.R
import com.fhhy.phoenix.test.MsgCenterBean

class MsgCenterAdapter : BaseQuickAdapter<MsgCenterBean, BaseViewHolder>(R.layout.item_msg_center) {

    override fun convert(holder: BaseViewHolder, item: MsgCenterBean) {
        holder.setImageResource(R.id.iv_msg_type, getMsgTypeIcon(item.type))
            .setText(R.id.msg_title, item.title)
            .setText(R.id.msg_date, item.date)
            .setText(R.id.msg_des, item.des)
            .setText(R.id.tv_unread_msg, "${item.unReadMsg}")
            .setText(R.id.tv_msg_type, getMsgTypeIconDes(item.type))
    }

    private fun getMsgTypeIcon(type: Int): Int = when(type) {
        0 -> R.drawable.ic_msg_center_customer_msg
        1 -> R.drawable.ic_msg_center_system
        else -> R.drawable.ic_msg_center_copy
    }
    private fun getMsgTypeIconDes(type: Int): Int = when(type) {
        0 -> R.string.msg_center_type_customer
        1 -> R.string.msg_center_type_system
        else -> R.string.msg_center_type_copy
    }
}