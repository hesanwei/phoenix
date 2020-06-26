package com.fhhy.phoenix.contractdetail.lastestdeal

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.fhhy.phoenix.R
import com.fhhy.phoenix.test.DealBean
import getTextColor
import kotlin.random.Random

class LatestDealAdapter : BaseQuickAdapter<DealBean, BaseViewHolder>(R.layout.item_latest_deal) {
    override fun convert(holder: BaseViewHolder, item: DealBean) {
        val testIsLong = Random.nextBoolean()
        val direction: String = context.resources.getString(if (testIsLong) R.string.direction_long else
        R.string.direction_short)
        val directionColor = context.getTextColor(testIsLong)
        holder.setText(R.id.deal_time, item.time)
            .setText(R.id.deal_direction, direction)
            .setTextColor(R.id.deal_direction, directionColor)
            .setText(R.id.deal_price, "${item.price}")
            .setText(R.id.deal_count, "${item.count}")
    }
}