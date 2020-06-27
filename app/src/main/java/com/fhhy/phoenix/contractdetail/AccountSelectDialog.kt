package com.fhhy.phoenix.contractdetail

import androidx.recyclerview.widget.LinearLayoutManager
import com.fhhy.phoenix.R
import com.fhhy.phoenix.contractdetail.adapter.AccountTypeSelectAdapter
import com.fhhy.phoenix.contractdetail.bean.AccountTypeBean
import com.fhhy.phoenix.dialog.BaseDialog
import kotlinx.android.synthetic.main.dialog_coin_select.*

class AccountSelectDialog(
    private val dataList: MutableList<AccountTypeBean>,
    private val listener: OnItemSelectListener
) : BaseDialog() {

    private val selectAdapter by lazy {
        AccountTypeSelectAdapter()
    }

    override fun getLayoutId(): Int = R.layout.dialog_coin_select

    override fun initView() {
        initRecyclerView()
    }

    private fun initRecyclerView() {
        recyclerView?.run {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = selectAdapter
        }
        //TODO 从sp中取
        selectAdapter.setSelectedPosition(0)
        selectAdapter.setList(dataList)

//        selectAdapter.setOnItemClickListener { adapter, view, position ->
//            listener.onItemSelect(position)
//            dismiss()
//        }
        selectAdapter.addChildClickViewIds(R.id.root)
        selectAdapter.setOnItemChildClickListener { adapter, view, position ->
            if (view.id == R.id.root){
                listener.onItemSelect(position)
                dismiss()
            }
        }
    }

    interface OnItemSelectListener {
        fun onItemSelect(position: Int)
    }
}