package com.fhhy.phoenix.community.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.fhhy.phoenix.R

/**
 * Created by hecuncun on 2020/7/5
 */
class TraderInfoAdapter:BaseQuickAdapter<String,BaseViewHolder>(R.layout.item_trader_info) {
    override fun convert(holder: BaseViewHolder, item: String) {
       item?:return
    }
}