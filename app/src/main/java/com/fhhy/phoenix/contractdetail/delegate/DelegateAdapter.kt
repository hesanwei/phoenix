package com.fhhy.phoenix.contractdetail.delegate

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.fhhy.phoenix.R
import com.fhhy.phoenix.test.DelegateBean

class DelegateAdapter() : BaseQuickAdapter<DelegateBean, BaseViewHolder>(R.layout.item_delegate_list) {
    override fun convert(holder: BaseViewHolder, item: DelegateBean) {

        holder.setText(R.id.long_count, item.longCount)
            .setText(R.id.short_count, item.shortCount)
            .setText(R.id.high_value, item.longPrice)
            .setText(R.id.low_value, item.lowPrice)
    }
}