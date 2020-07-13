package com.fhhy.phoenix.bean


import com.google.gson.annotations.SerializedName

data class UnReadNum(
    @SerializedName("unread")
    val unread: Int
)