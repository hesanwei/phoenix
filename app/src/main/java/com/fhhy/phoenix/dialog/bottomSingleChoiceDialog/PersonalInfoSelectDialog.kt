package com.fhhy.phoenix.dialog.bottomSingleChoiceDialog

import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.fhhy.phoenix.R
import com.fhhy.phoenix.dialog.BaseDialog
import kotlinx.android.synthetic.main.dialog_personal_info_select.*

// Created by admin on 2020/7/11.
class PersonalInfoSelectDialog(
    private val itemsList: MutableList<PersonalInfoSelectBean>,
    private val listener: OnItemClickListener?
) : BaseDialog() {
    override fun getLayoutId(): Int = R.layout.dialog_personal_info_select

    override fun initView() {
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(context)
        val adapter = PersonalInfoSelectAdapter(itemsList)
        adapter.setOnItemClickListener { adapter, view, position ->
            listener?.onItemClick(itemsList[position])
            dismiss()
        }
        recyclerView.adapter = adapter
        cvCancel.setOnClickListener {
            dismiss()
        }
    }

    inner class PersonalInfoSelectAdapter(list: MutableList<PersonalInfoSelectBean>) :
        BaseQuickAdapter<PersonalInfoSelectBean, BaseViewHolder>(
            R.layout.item_personal_info_select,
            list
        ) {
        override fun convert(holder: BaseViewHolder, item: PersonalInfoSelectBean) {
            holder.setText(R.id.tvName, item.name)
        }
    }

    interface OnItemClickListener {
        fun onItemClick(bean: PersonalInfoSelectBean?)
    }
}