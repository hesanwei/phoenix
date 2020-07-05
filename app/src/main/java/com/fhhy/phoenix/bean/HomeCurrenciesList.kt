package com.fhhy.phoenix.bean
import com.google.gson.annotations.SerializedName

data class HomeCurrenciesList(
    @SerializedName("currency_price_list")
    val currencyPriceList: List<CurrencyPrice>
)