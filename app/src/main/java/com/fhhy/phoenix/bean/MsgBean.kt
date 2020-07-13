package com.fhhy.phoenix.bean


import com.google.gson.annotations.SerializedName

data class MsgListPageBean(
    @SerializedName("current_page")
    val currentPage: Int,
    @SerializedName("data")
    val msgList: List<MsgItem>,
    @SerializedName("last_page")
    val lastPage: Int,
    @SerializedName("per_page")
    val perPage: Int,
    @SerializedName("total")
    val total: Int
)

data class MsgItem(
    @SerializedName("button_name")
    val buttonName: String,
    @SerializedName("href")
    val href: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("msg")
    val msg: String,
    @SerializedName("send_time")
    val sendTime: String,
    @SerializedName("status")
    val status: Int,
    @SerializedName("title")
    val title: String
)