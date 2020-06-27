package com.fhhy.phoenix.contractdetail.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.fhhy.phoenix.R
import com.fhhy.phoenix.contractdetail.bean.AccountTypeBean

class AccountTypeSelectAdapter :
    BaseQuickAdapter<AccountTypeBean, BaseViewHolder>(R.layout.item_account_type) {
    var mSelectedPosition = 0
    fun setSelectedPosition(position: Int) {
        mSelectedPosition = position
        notifyDataSetChanged()
    }

    override fun convert(holder: BaseViewHolder, item: AccountTypeBean) {
        val selected = holder.adapterPosition == mSelectedPosition
        val color = context.resources.getColor(if (!selected) R.color.black else R.color.text_main_blue)
        holder.setText(R.id.account_type_name, item.accountType)
            .setImageResource(R.id.account_type_logo, item.drawableRes)
            .setTextColor(R.id.account_type_name, color)
            .setGone(R.id.account_selected, !selected)
            .setGone(R.id.divider, holder.adapterPosition == (itemCount - 1))
    }
}