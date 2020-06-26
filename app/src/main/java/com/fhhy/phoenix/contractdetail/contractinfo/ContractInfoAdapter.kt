package com.fhhy.phoenix.contractdetail.contractinfo

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.entity.MultiItemEntity
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.fhhy.phoenix.R
import com.fhhy.phoenix.test.PriceSourceBean
import com.fhhy.phoenix.test.TradeInfoBean

const val ITEM_TYPE_TITLE_PRICE_SOURCE = 0
const val ITEM_TYPE_TITLE_TRADE_INFO = 1
const val ITEM_TYPE_PRICE_NORMAL = 2
const val ITEM_TYPE_TRADE_NORMAL = 3
class ContractInfoAdapter : BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder>() {
    init {
        addItemType(ITEM_TYPE_TITLE_PRICE_SOURCE, R.layout.item_title_price_source)
        addItemType(ITEM_TYPE_TITLE_TRADE_INFO, R.layout.item_title_trade_info)
        addItemType(ITEM_TYPE_PRICE_NORMAL, R.layout.item_price_source_normal)
        addItemType(ITEM_TYPE_TRADE_NORMAL, R.layout.item_trade_info_normal)
    }
    override fun convert(holder: BaseViewHolder, item: MultiItemEntity) {
        when(item.itemType) {
            ITEM_TYPE_PRICE_NORMAL -> {
                val priceItem = (item as NormalPriceItem).priceSourceBean
                holder.setText(R.id.exchange_location_value, priceItem.exchangeLocation)
                    .setText(R.id.weight_value, priceItem.weight)
                    .setText(R.id.latest_price_value, priceItem.latestPrice)
            }
            ITEM_TYPE_TRADE_NORMAL -> {
                val tradeInfoBean = (item as NormalTradeInfo).tradeInfoBean
                holder.setText(R.id.margin_value, tradeInfoBean.margin)
                    .setText(R.id.max_long_value, tradeInfoBean.maxLongValue)
                    .setText(R.id.max_short_value, tradeInfoBean.maxShortValue)
                    .setText(R.id.max_leverage_value, tradeInfoBean.maxLeverage)
                    .setGone(R.id.divider, holder.adapterPosition == (itemCount-1))
            }
        }
    }

    class TitlePriceItem : MultiItemEntity {
        override val itemType: Int
            get() = ITEM_TYPE_TITLE_PRICE_SOURCE
    }
    class NormalPriceItem(val priceSourceBean: PriceSourceBean) : MultiItemEntity {
        override val itemType: Int
            get() = ITEM_TYPE_PRICE_NORMAL
    }
    class TitleTradeInfo : MultiItemEntity {
        override val itemType: Int
            get() = ITEM_TYPE_TITLE_TRADE_INFO
    }

    class NormalTradeInfo(val tradeInfoBean: TradeInfoBean) : MultiItemEntity {
        override val itemType: Int
            get() = ITEM_TYPE_TRADE_NORMAL

    }
}