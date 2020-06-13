package com.fhhy.phoenix.contract.fragment

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.fhhy.phoenix.R
import com.fhhy.phoenix.test.ContractBean

class ContractAdapter : BaseQuickAdapter<ContractBean, BaseViewHolder>(R.layout.item_contract_layout) {

    override fun convert(holder: BaseViewHolder, item: ContractBean) {
        holder.setText(R.id.tv_currency_name, item.currencyName)
            .setText(R.id.tv_curr_price, "${item.price}")
            .setText(R.id.tv_quote_change, "${item.quoteChange}")
    }
}