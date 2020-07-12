package com.fhhy.phoenix.message.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.fhhy.phoenix.R
import com.fhhy.phoenix.test.MsgCenterBean
import com.mcxtzhang.swipemenulib.SwipeMenuLayout

class MsgItemAdapter : BaseQuickAdapter<MsgCenterBean, BaseViewHolder>(R.layout.item_msg_item) {

    override fun convert(holder: BaseViewHolder, item: MsgCenterBean) {
        holder.setText(R.id.msg_title, item.title)
            .setText(R.id.msg_date, item.date)
            .setText(R.id.msg_des, item.des)
        //设置是否可以侧滑删除
        holder.getView<SwipeMenuLayout>(R.id.swipe_menu_layout).isSwipeEnable = true
    }
}