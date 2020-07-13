package com.fhhy.phoenix.bean
import com.google.gson.annotations.SerializedName

data class MsgCenterBean(
    @SerializedName("title")
    val title: String,
    @SerializedName("msg")
    val msg: String,
    @SerializedName("send_time")
    val sendTime: String,
    @SerializedName("type")
    val type: Int,
    @SerializedName("unread_num")
    val unreadNum: Int
)