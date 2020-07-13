package com.fhhy.phoenix.message

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.fhhy.phoenix.R
import com.fhhy.phoenix.base.BaseVBFragment
import com.fhhy.phoenix.bean.MsgItem
import com.fhhy.phoenix.bean.RequestStatus
import com.fhhy.phoenix.bean.ResultData
import com.fhhy.phoenix.databinding.FragmentMsgListBinding
import com.fhhy.phoenix.message.adapter.MsgItemAdapter
import com.fhhy.phoenix.message.viewmodel.MsgListViewModel
import com.fhhy.phoenix.test.MsgCenterBean
import com.jaeger.library.StatusBarUtil
import io.reactivex.android.schedulers.AndroidSchedulers
import noDoubleClick
import showToast

class MsgListFragment : BaseVBFragment<FragmentMsgListBinding>() {
    private val mAdapter: MsgItemAdapter by lazy {
        MsgItemAdapter()
    }

    private val mViewModel: MsgListViewModel by lazy {
        ViewModelProvider(this, MsgListViewModel.Factory(msgType)).get(MsgListViewModel::class.java)
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
               R.id.content -> {
               }
           }
        }
    }

    override fun setupObservers() {
        mViewModel.msgList
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext {
                setupData(it)
            }
            .subscribe()
    }

    private fun setupData(data: ResultData<List<MsgItem>>) {
        when(data.status) {
            RequestStatus.LOADING -> {
                showLoading()
            }
            RequestStatus.SUCCESS -> {
                if (mAdapter.data.isEmpty()) {
                    mAdapter.setList(data.data)
                }else {
                    data.data?.run {
                        mAdapter.addData(this)
                    }
                }
                mAdapter.loadMoreModule.loadMoreComplete()
                hideLoading()
            }
            RequestStatus.ERROR -> {
                showToast(data.errorMsg)
                hideLoading()
            }
            RequestStatus.COMPLETE -> {
                mAdapter.loadMoreModule.loadMoreEnd()
                hideLoading()
            }
            RequestStatus.EMPTY -> {
                hideLoading()
            }
        }
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