package com.fhhy.phoenix.message

import android.os.Bundle
import android.util.Log
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
import com.fhhy.phoenix.event.UpdateMsgUnReadNumEvent
import com.fhhy.phoenix.message.adapter.MsgItemAdapter
import com.fhhy.phoenix.message.viewmodel.MsgListViewModel
import com.fhhy.phoenix.test.MsgCenterBean
import com.jaeger.library.StatusBarUtil
import io.reactivex.android.schedulers.AndroidSchedulers
import noDoubleClick
import org.greenrobot.eventbus.EventBus
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

        mAdapter.apply {
            addChildClickViewIds(R.id.content)

            setOnItemChildClickListener { adapter, view, position ->
                when(view.id) {
                    R.id.content -> {
                        responseClickEvent(mAdapter.data[position])
                    }
                }
            }
            loadMoreModule.setOnLoadMoreListener {
                mViewModel.requestMorePage()
            }
        }

    }

    private fun responseClickEvent(msgItem: MsgItem) {
        //TODO 点击后打开消息h5页面

        if (msgItem.status == 0) {
            Log.d(TAG, "responseClickEvent: id--> $id")
            mViewModel.consumeMsgRead(msgItem.id)
        }

    }

    override fun setupObservers() {
        val msgList = mViewModel.msgList
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext {
                setupData(it)
            }
            .subscribe()

        mDisposables.add(msgList)

        val consumeMsgSuccess = mViewModel.consumeMsgSuccess
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext {
                EventBus.getDefault().post(UpdateMsgUnReadNumEvent())
            }
            .subscribe()
        mDisposables.add(consumeMsgSuccess)
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
                if (mAdapter.data.isEmpty()) {
                    showToast(data.errorMsg)
                }else{
                    mAdapter.loadMoreModule.loadMoreFail()
                }
                hideLoading()
            }
            RequestStatus.COMPLETE -> {
                mAdapter.loadMoreModule.loadMoreEnd()
                hideLoading()
            }
            RequestStatus.EMPTY -> {
                //TODO show empty page
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