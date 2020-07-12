package com.fhhy.phoenix.mine.adapter

import android.graphics.Color
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.fhhy.phoenix.R

// Created by admin on 2020/7/12.
class InviteRecordAdapter : BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_invite_record) {
    override fun convert(holder: BaseViewHolder, item: String) {
        holder.setBackgroundColor(
            R.id.item,
            Color.parseColor(if (holder.adapterPosition % 2 == 0) "#404350" else "#282D3D")
        )
    }
}