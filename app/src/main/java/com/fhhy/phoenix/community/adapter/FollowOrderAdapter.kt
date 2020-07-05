package com.fhhy.phoenix.community.adapter

import android.graphics.Paint
import android.text.SpannableString
import android.text.Spanned
import android.text.style.UnderlineSpan
import android.widget.TextView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.fhhy.phoenix.R
import underline

/**
 * Created by hecuncun on 2020/7/5
 */
class FollowOrderAdapter:BaseQuickAdapter<String,BaseViewHolder>(R.layout.item_follow_order) {
    private val tagAdapter:TagAdapter by lazy {
        TagAdapter()
    }
    override fun convert(holder: BaseViewHolder, item: String) {
         item?:return
        val recyclerView = holder.getView<RecyclerView>(R.id.recyclerView)
        //使用此方式设置下划线为了适配华为手机有双下划线
        val tvWithdrawal = holder.getView<TextView>(R.id.tv_withdrawal_rate_recent_3)
        tvWithdrawal.underline()
        recyclerView.run {
            layoutManager = LinearLayoutManager(context,RecyclerView.HORIZONTAL,false)
            adapter=tagAdapter
            tagAdapter.data = mutableListOf("", "", "","")
        }
    }

}