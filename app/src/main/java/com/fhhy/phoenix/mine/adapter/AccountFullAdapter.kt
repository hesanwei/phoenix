package com.fhhy.phoenix.mine.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.fhhy.phoenix.R

// Created by admin on 2020/6/14.
class AccountFullAdapter : BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_account_full) {
    override fun convert(holder: BaseViewHolder, item: String) {
        holder.run {
            setText(R.id.tvCurrency, item)
            setText(
                R.id.tvOutCurrency,
                String.format(context.resources.getString(R.string.transfer_out_coin), item)
            )
            setText(
                R.id.tvInCurrency,
                String.format(context.resources.getString(R.string.transfer_in_coin), item)
            )
        }
    }
}