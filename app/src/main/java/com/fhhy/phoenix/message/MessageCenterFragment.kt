package com.fhhy.phoenix.message

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.fhhy.phoenix.R
import com.fhhy.phoenix.base.BaseVBFragment
import com.fhhy.phoenix.bean.MsgCenterBean
import com.fhhy.phoenix.bean.RequestStatus
import com.fhhy.phoenix.bean.ResultData
import com.fhhy.phoenix.databinding.FragmentMsgCenterBinding
import com.fhhy.phoenix.message.adapter.MsgCenterAdapter
import com.fhhy.phoenix.message.viewmodel.MsgCenterViewModel
import com.jaeger.library.StatusBarUtil
import io.reactivex.android.schedulers.AndroidSchedulers
import noDoubleClick
import showToast

class MessageCenterFragment : BaseVBFragment<FragmentMsgCenterBinding>() {
    private val mAdapter: MsgCenterAdapter by lazy {
        MsgCenterAdapter()
    }

    private val mViewModel: MsgCenterViewModel by lazy {
        ViewModelProvider(
            this,
            MsgCenterViewModel.Factory()
        ).get(MsgCenterViewModel::class.java)
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

        val customMsg = LayoutInflater.from(requireContext())
            .inflate(R.layout.item_msg_center_head, mBinding.rvMsg.parent as ViewGroup, false)
        mAdapter.addHeaderView(customMsg)
        customMsg.findViewById<View>(R.id.container).setOnClickListener {
            //TODO 跳转三方客服

        }
    }

    override fun setupListeners() {
        addDisposable {
            mBinding.appBar.btnBack.noDoubleClick {
                requireActivity().finish()
            }
        }

        mAdapter.setOnItemClickListener { adapter, view, position ->
            startActivity(Intent(requireContext(), MsgListActivity::class.java).apply {
                putExtra(TYPE_MSG, mAdapter.getItem(position).type)
            })
        }
    }

    override fun setupObservers() {
        mViewModel.msgCenterBeen
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext {
                setupData(it)
            }
            .subscribe()
    }

    private fun setupData(data: ResultData<List<MsgCenterBean>>) {
        when (data.status) {
            RequestStatus.SUCCESS -> {
                mAdapter.setList(data.data)
                hideLoading()
            }
            RequestStatus.LOADING -> {
                showLoading()
            }
            RequestStatus.ERROR -> {
                showToast(data.errorMsg)
                hideLoading()
            }
        }
    }

    companion object {
        const val TAG = "MessageCenterFragment"
        fun create(): MessageCenterFragment {
            return MessageCenterFragment().apply {

            }
        }
    }
}