package com.fhhy.phoenix.test

data class ContractBean(
    val currencyName: String,
    val price: Float, //当前价格
    val quoteChange: Float//涨跌幅
)