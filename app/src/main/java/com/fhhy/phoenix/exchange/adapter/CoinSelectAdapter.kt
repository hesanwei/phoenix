package com.fhhy.phoenix.exchange.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.fhhy.phoenix.R
import java.sql.Struct

// Created by admin on 2020/6/13.
class CoinSelectAdapter : BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_coin_select) {
    override fun convert(holder: BaseViewHolder, item: String) {
        holder.setText(R.id.tvCoinName, item)
    }
}