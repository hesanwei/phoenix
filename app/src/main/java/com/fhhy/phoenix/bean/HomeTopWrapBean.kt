package com.fhhy.phoenix.bean

import com.google.gson.annotations.SerializedName
import com.stx.xhb.xbanner.entity.SimpleBannerInfo

data class HomeTopWrapBean(
    @SerializedName("ad_list")
    val bannerList: List<BannerBean>,
    @SerializedName("invitation")
    val invitation: String,
    @SerializedName("notice_list")
    val noticeList: List<Notice>,
    @SerializedName("novice_guide")
    val noviceGuide: String,
    @SerializedName("platform_introduction")
    val platformIntroduction: String
)

data class BannerBean(
    @SerializedName("ad_image")
    val image: String,
    @SerializedName("ad_link")
    val link: String
) : SimpleBannerInfo() {
    override fun getXBannerUrl(): String = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1592038610007&di=d0831bd19797c6d62bbb330ed1d0c43f&imgtype=0&src=http%3A%2F%2Fa3.att.hudong.com%2F14%2F75%2F01300000164186121366756803686.jpg"
}

data class Notice(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String
)