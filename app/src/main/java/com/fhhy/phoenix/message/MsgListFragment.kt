package com.fhhy.phoenix.message

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.fhhy.phoenix.R
import com.fhhy.phoenix.base.BaseVBFragment
import com.fhhy.phoenix.databinding.FragmentMsgCenterBinding
import com.fhhy.phoenix.databinding.FragmentMsgListBinding
import com.fhhy.phoenix.message.adapter.MsgCenterAdapter
import com.fhhy.phoenix.message.adapter.MsgItemAdapter
import com.fhhy.phoenix.test.MsgCenterBean
import com.jaeger.library.StatusBarUtil
import kotlinx.android.synthetic.main.item_msg_item.view.*
import noDoubleClick

class MsgListFragment : BaseVBFragment<FragmentMsgListBinding>() {
    private val mAdapter: MsgItemAdapter by lazy {
        MsgItemAdapter()
    }

    private val msgType : Int by lazy {
        arguments?.getInt(TYPE_MSG) ?: 0
    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentMsgListBinding =
        FragmentMsgListBinding.inflate(inflater, container, false)

    override fun setupViews() {
        StatusBarUtil.setTransparentForImageView(activity, mBinding.appBar.root)
        StatusBarUtil.setLightMode(activity)

        mBinding.rvMsgList.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = mAdapter
        }

    }

    override fun setupListeners() {
        addDisposable {
            mBinding.appBar.btnBack.noDoubleClick {
                requireActivity().finish()
            }
        }

        mAdapter.addChildClickViewIds(R.id.delete, R.id.content)
        mAdapter.setOnItemChildClickListener { adapter, view, position ->
           when(view.id) {
               R.id.delete -> mAdapter.removeAt(position)
               R.id.content -> {
                   //TODO
               }
           }
        }
    }

    override fun setupObservers() {
        val data = mutableListOf<MsgCenterBean>()
        for (i in 0..2) {
            data.add(MsgCenterBean(i,
                "2020-7-12 11:55",
                "测试消息",
                "跟随柳惊涛成功开仓BTC/USDT订单，开仓价9,426.41。跟随订单号：2568791。交易员订单号…",
            12))
        }
        mAdapter.setList(data)
    }

    companion object {
        const val TAG = "MsgListFragment"
        fun create(msgType: Int): MsgListFragment {
            return MsgListFragment().apply {
                arguments = Bundle().apply {
                    putInt(TYPE_MSG, msgType)
                }
            }
        }
    }
}