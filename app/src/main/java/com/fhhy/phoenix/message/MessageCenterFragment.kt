package com.fhhy.phoenix.message

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.fhhy.phoenix.base.BaseVBFragment
import com.fhhy.phoenix.databinding.FragmentMsgCenterBinding
import com.fhhy.phoenix.message.adapter.MsgCenterAdapter
import com.fhhy.phoenix.test.MsgCenterBean
import com.jaeger.library.StatusBarUtil
import noDoubleClick

class MessageCenterFragment : BaseVBFragment<FragmentMsgCenterBinding>() {
    private val mAdapter: MsgCenterAdapter by lazy {
        MsgCenterAdapter()
    }
    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentMsgCenterBinding =
        FragmentMsgCenterBinding.inflate(inflater, container, false)

    override fun setupViews() {
        StatusBarUtil.setTransparentForImageView(activity, mBinding.appBar.root)
        StatusBarUtil.setLightMode(activity)

        mBinding.rvMsg.apply {
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

        addDisposable {
            mBinding.btnClose.noDoubleClick {
                mBinding.openNotificationContainer.isVisible = false
            }
        }

        addDisposable {
            mBinding.btnOpenNotf.noDoubleClick {
                //TODO
            }
        }

        mAdapter.setOnItemClickListener { adapter, view, position ->
            startActivity(Intent(requireContext(), MsgListActivity::class.java))
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
        const val TAG = "MessageCenterFragment"
        fun create() : MessageCenterFragment {
            return MessageCenterFragment().apply {

            }
        }
    }
}