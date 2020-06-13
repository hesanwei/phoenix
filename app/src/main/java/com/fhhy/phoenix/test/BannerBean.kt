package com.fhhy.phoenix.test

import com.stx.xhb.xbanner.entity.BaseBannerInfo
import com.stx.xhb.xbanner.entity.SimpleBannerInfo

// Created by admin on 2020/6/13.
class BannerBean: SimpleBannerInfo() {
    override fun getXBannerUrl(): Any = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1592038610007&di=d0831bd19797c6d62bbb330ed1d0c43f&imgtype=0&src=http%3A%2F%2Fa3.att.hudong.com%2F14%2F75%2F01300000164186121366756803686.jpg"
}