package com.fhhy.phoenix.home

import androidx.recyclerview.widget.DiffUtil
import com.fhhy.phoenix.bean.CurrencyPrice

class CurrencyPriceItemDiff : DiffUtil.ItemCallback<CurrencyPrice>() {
    override fun areItemsTheSame(oldItem: CurrencyPrice, newItem: CurrencyPrice): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: CurrencyPrice, newItem: CurrencyPrice): Boolean {
        var same = true

        same = oldItem.price == newItem.price && same
        same = same && oldItem.range == newItem.range
        same = same && oldItem.trend == newItem.trend

        return same
    }

    //如果要精确打到item的某个属性改变了 那么可以实现这个
    override fun getChangePayload(oldItem: CurrencyPrice, newItem: CurrencyPrice): Any? {
        return null
    }
}