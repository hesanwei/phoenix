package com.fhhy.phoenix.message

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.fhhy.phoenix.base.BaseVBFragment
import com.fhhy.phoenix.bean.MsgCenterBean
import com.fhhy.phoenix.bean.RequestStatus
import com.fhhy.phoenix.bean.ResultData
import com.fhhy.phoenix.databinding.FragmentMsgCenterBinding
import com.fhhy.phoenix.event.UpdateMsgUnReadNumEvent
import com.fhhy.phoenix.message.viewmodel.MsgCenterViewModel
import com.jaeger.library.StatusBarUtil
import io.reactivex.android.schedulers.AndroidSchedulers
import noDoubleClick
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import showToast

class MessageCenterFragment : BaseVBFragment<FragmentMsgCenterBinding>() {
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
        EventBus.getDefault().register(this)
    }

    override fun setupListeners() {
        addDisposable {
            mBinding.appBar.btnBack.noDoubleClick {
                requireActivity().finish()
            }
        }

        addDisposable {
            mBinding.msgCustomer.noDoubleClick {
                //TODO
            }
        }
        addDisposable {
            mBinding.msgSystem.noDoubleClick {
                startActivity(Intent(requireContext(), MsgListActivity::class.java).apply {
                    putExtra(TYPE_MSG, 1)
                })
            }
        }
        addDisposable {
            mBinding.msgCopy.noDoubleClick {
                startActivity(Intent(requireContext(), MsgListActivity::class.java).apply {
                    putExtra(TYPE_MSG, 2)
                })
            }
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
                data.data?.onEach {
                    when (it.type) {
                        1 -> {
                            updateMsgSysItem(it)
                        }
                        2 -> {
                            updateMsgCopyItem(it)
                        }
                    }
                }
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

    private fun updateMsgSysItem(msg: MsgCenterBean) {
        mBinding.tvUnreadMsgSys.isVisible = msg.unreadNum != 0
        mBinding.tvUnreadMsgSys.text = "${msg.unreadNum}"
    }

    private fun updateMsgCopyItem(msg: MsgCenterBean) {
        mBinding.tvUnreadMsgCopy.isVisible = msg.unreadNum != 0
        mBinding.tvUnreadMsgCopy.text = "${msg.unreadNum}"
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onReceiveUpdateMsgEvent(event: UpdateMsgUnReadNumEvent) {
        Log.d(TAG, "onReceiveUpdateMsgEvent: -->")
        mViewModel.updateMsgCenterItems()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        EventBus.getDefault().unregister(this)
    }

    companion object {
        const val TAG = "MessageCenterFragment"
        fun create(): MessageCenterFragment {
            return MessageCenterFragment().apply {

            }
        }
    }
}