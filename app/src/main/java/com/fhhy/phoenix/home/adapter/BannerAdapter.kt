package com.fhhy.phoenix.home.adapter

import android.content.Context
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.fhhy.phoenix.bean.BannerBean
import com.stx.xhb.xbanner.XBanner

// Created by admin on 2020/6/13.
class BannerAdapter(private val context: Context) : XBanner.XBannerAdapter {
    override fun loadBanner(banner: XBanner?, model: Any?, view: View?, position: Int) {
        Glide.with(context).load((model as BannerBean).xBannerUrl).into(view as ImageView)
        //TODO banner 跳转
        view.setOnClickListener {

        }
    }
}