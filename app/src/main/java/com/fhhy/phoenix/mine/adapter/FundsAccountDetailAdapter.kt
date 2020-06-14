package com.fhhy.phoenix.mine.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.fhhy.phoenix.R

// Created by admin on 2020/6/14.
class FundsAccountDetailAdapter: BaseQuickAdapter<String,BaseViewHolder>(R.layout.item_funds_account_detail) {
    override fun convert(holder: BaseViewHolder, item: String) {
        holder.run {
            setText(R.id.tvTransferType,"资金划转-转出")
            setText(R.id.tvTransferFunds,"-20.00 USDT")
            setText(R.id.tvTime,"20分钟前")
            setText(R.id.tvStatus,"已确认")
        }
    }
}