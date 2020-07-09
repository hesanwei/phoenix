package com.fhhy.phoenix.community.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.fhhy.phoenix.R

/**
 * Created by heCunCun on 2020/7/9
 */
class HistoricalPositionAdapter :BaseQuickAdapter<String,BaseViewHolder>(R.layout.item_historical_position) {
    override fun convert(holder: BaseViewHolder, item: String) {
       item?:return
    }
}