package com.fhhy.phoenix.contract.adapter

import androidx.core.text.HtmlCompat
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.fhhy.phoenix.R
import com.fhhy.phoenix.bean.CurrencyPrice
import isUp
import kotlin.random.Random

class ContractAdapter :
    BaseQuickAdapter<CurrencyPrice, BaseViewHolder>(R.layout.item_contract_layout) {

    override fun convert(holder: BaseViewHolder, item: CurrencyPrice) {
        val nameHolder =
            String.format(context.resources.getString(R.string.currency_name), item.title)
        val fromHtml = HtmlCompat.fromHtml(nameHolder, HtmlCompat.FROM_HTML_MODE_COMPACT)
        val up = item.trend.isUp
        holder.setText(R.id.tv_currency_name, fromHtml)
            .setTextColor(
                R.id.tv_curr_price,
                context.resources.getColor(if (up) R.color.currency_up_color else R.color.currency_down_color)
            )
            .setText(R.id.tv_curr_price, item.price)
            .setBackgroundResource(
                R.id.tv_quote_change,
                if (up) R.drawable.bg_btn_currency_up else R.drawable.bg_btn_currency_down
            )
            .setText(R.id.tv_quote_change, item.range)
    }
}