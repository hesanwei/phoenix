package com.fhhy.phoenix.mine.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.fhhy.phoenix.R
import com.fhhy.phoenix.bean.BonusBean

// Created by admin on 2020/6/21.
class BonusAdapter : BaseQuickAdapter<BonusBean, BaseViewHolder>(R.layout.item_bonus) {
    override fun convert(holder: BaseViewHolder, item: BonusBean) {
        holder.apply {
            item.apply {
                setText(R.id.tvMoney, money)
                setText(
                    R.id.tvBonusNote,
                    String.format(
                        context.resources.getString(R.string.up_leverage_limit),
                        upper_limit
                    )
                )
                setText(R.id.tvBonusType, source_type)
                setText(R.id.tvBonusStatus, getBonusStatus(is_expired, is_used))
                setText(R.id.tvEffectiveDate, getEffectTime(expire_time, effective_time))
            }
        }
    }

    private fun getEffectTime(expireTime: String?, effectiveTime: String?): String {
        if ("0" == expireTime) {
            return context.getString(R.string.effective_forever)
        }
        return String.format(context.getString(R.string.effect_time), effectiveTime, expireTime)
    }

    private fun getBonusStatus(is_expired: String?, is_used: String?): String {
        if ("1" == is_expired) {
            return context.getString(R.string.overdue)
        }
        return when (is_used) {
            "0" -> context.getString(R.string.now_use)
            "1" -> context.getString(R.string.used)
            "2" -> context.getString(R.string.no_effect)
            else -> context.getString(R.string.now_use)
        }
    }
}