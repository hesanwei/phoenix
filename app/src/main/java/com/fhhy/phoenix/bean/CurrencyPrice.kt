package com.fhhy.phoenix.bean


import com.google.gson.annotations.SerializedName

data class CurrencyPrice(
    @SerializedName("price")
    val price: String,
    @SerializedName("range")
    val range: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("trend")
    val trend: String
)