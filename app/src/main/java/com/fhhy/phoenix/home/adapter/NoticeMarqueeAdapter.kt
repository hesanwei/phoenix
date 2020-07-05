package com.fhhy.phoenix.home.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import com.fhhy.phoenix.R
import com.stx.xmarqueeview.XMarqueeView
import com.stx.xmarqueeview.XMarqueeViewAdapter

// Created by admin on 2020/6/13.
class NoticeMarqueeAdapter() :
    XMarqueeViewAdapter<String>(listOf("")) {
    @SuppressLint("SetTextI18n")
    override fun onBindView(parent: View?, view: View?, position: Int) {
        view?.findViewById<AppCompatTextView>(R.id.tvNoticeContent)?.text = mDatas[position]
    }

    override fun onCreateView(parent: XMarqueeView?): View {
        return LayoutInflater.from(parent?.context).inflate(R.layout.item_home_notice, null)
    }
}