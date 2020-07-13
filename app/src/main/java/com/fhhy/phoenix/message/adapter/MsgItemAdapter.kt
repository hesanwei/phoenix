package com.fhhy.phoenix.message.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.module.LoadMoreModule
import com.chad.library.adapter.base.module.LoadMoreModuleConfig
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.fhhy.phoenix.R
import com.fhhy.phoenix.bean.MsgItem
import com.fhhy.phoenix.test.MsgCenterBean
import com.mcxtzhang.swipemenulib.SwipeMenuLayout

class MsgItemAdapter : BaseQuickAdapter<MsgItem, BaseViewHolder>(R.layout.item_msg_item), LoadMoreModule{

    override fun convert(holder: BaseViewHolder, item: MsgItem) {
        holder.setText(R.id.msg_title, item.title)
            .setText(R.id.msg_date, item.sendTime)
            .setText(R.id.msg_des, item.msg)
            .setText(R.id.look_detail, item.buttonName)
    }
}