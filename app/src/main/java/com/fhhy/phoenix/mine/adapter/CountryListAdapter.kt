package com.fhhy.phoenix.mine.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.fhhy.phoenix.R
import com.fhhy.phoenix.bean.CountryListBean

// Created by admin on 2020/7/12.
class CountryListAdapter :
    BaseQuickAdapter<CountryListBean, BaseViewHolder>(R.layout.item_country_list) {
    override fun convert(holder: BaseViewHolder, item: CountryListBean) {
        holder.apply {
            item.apply {
                setText(R.id.tvCountryName, name)
                setText(R.id.tvCountryCode, code)
            }
        }
    }
}