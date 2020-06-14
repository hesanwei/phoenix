package com.fhhy.phoenix.mine.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.fhhy.phoenix.R

// Created by admin on 2020/6/14.
class AccountFundsAdapter: BaseQuickAdapter<String,BaseViewHolder>(R.layout.item_account_funds) {
    override fun convert(holder: BaseViewHolder, item: String) {
        holder.run {
            setText(R.id.tvCurrency,item)
            setText(R.id.tvFunds,"0.00")
        }
    }
}