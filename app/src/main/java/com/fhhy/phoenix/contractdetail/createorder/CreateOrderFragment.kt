package com.fhhy.phoenix.contractdetail.createorder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.fhhy.phoenix.R
import com.fhhy.phoenix.base.BaseVBFragment
import com.fhhy.phoenix.contractdetail.AccountSelectDialog
import com.fhhy.phoenix.contractdetail.ContractDetailFragment
import com.fhhy.phoenix.contractdetail.bean.AccountTypeBean
import com.fhhy.phoenix.databinding.FragmentCreateOrderBinding
import noDoubleClick
import showToast
import underline

class CreateOrderFragment : BaseVBFragment<FragmentCreateOrderBinding>() {

    private val accountTypes: MutableList<AccountTypeBean> by lazy {
        mutableListOf(
            AccountTypeBean(R.drawable.ic_account_type_btc, resources.getString(R.string.account_btc)),
            AccountTypeBean(R.drawable.ic_account_type_usdt, resources.getString(R.string.account_usdt)),
            AccountTypeBean(R.drawable.ic_account_type_vst, resources.getString(R.string.account_vst)),
            AccountTypeBean(R.drawable.ic_account_type_bonus, resources.getString(R.string.account_usdt_bonus))
        )
    }

    private var isLong: Boolean = true

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentCreateOrderBinding = FragmentCreateOrderBinding.inflate(inflater, container, false)

    override fun setupViews() {

        //触发价 设置
        mBinding.triggerContainer.triggerSetting.underline()
        //预计成交价
        mBinding.bottomContainer.tvEstimated.underline()
        //重置
        mBinding.bottomContainer.tvRecharge.underline()
        //高级设置
        mBinding.bottomContainer.tvAdvance.underline()

    }

    override fun setupListeners() {
        addDisposable {
            mBinding.longShortSwitchContainer.radioOpen.noDoubleClick {
                if (!isLong) {
                    switchLongOrShortTab(true)
                }
            }
        }
        addDisposable {
            mBinding.longShortSwitchContainer.radioClose.noDoubleClick {
                if (isLong) {
                    switchLongOrShortTab(false)
                }
            }
        }

        addDisposable {
            mBinding.bottomContainer.tvAdvance.noDoubleClick {
                showToast("高级设置")
            }
        }
        addDisposable {
            mBinding.bottomContainer.tvRecharge.noDoubleClick {
                showToast("重置")
            }
        }
        addDisposable {
            mBinding.bottomContainer.tvEstimated.noDoubleClick {
                showToast("预计成交价")
            }
        }

        addDisposable {
            mBinding.triggerContainer.triggerSetting.noDoubleClick {
                showToast("触发价设置")
            }
        }

        addDisposable {
            mBinding.longShortSwitchContainer.btnHideCreateOrder.noDoubleClick {
                val parentFragment = requireActivity().supportFragmentManager.findFragmentByTag(
                    ContractDetailFragment.TAG
                )
                parentFragment?.apply {
                    if (this is ContractDetailFragment) {
                        switchMode()
                    }
                }
            }
        }

        addDisposable {
            mBinding.modeContainer.accountType.noDoubleClick {
                AccountSelectDialog(accountTypes, object : AccountSelectDialog.OnItemSelectListener{
                    override fun onItemSelect(position: Int) {
                        showToast(accountTypes[position].accountType)
                        mBinding.modeContainer.accountType.text = accountTypes[position].accountType
                    }
                }).show(childFragmentManager, "AccountSelectDialog")
            }
        }
    }

    override fun setupObservers() {
    }

    fun switchLongOrShortTab(long: Boolean) {
        isLong = long

        mBinding.longShortSwitchContainer.tvLong.setTextColor(
            if (long) resources.getColor(R.color.currency_up_color)
        else resources.getColor(R.color.text_main_gray)
        )
        mBinding.longShortSwitchContainer.longSelected.visibility = if (long)
            View.VISIBLE else View.INVISIBLE

        mBinding.longShortSwitchContainer.tvShort.setTextColor(
            if (!long) resources.getColor(R.color.currency_down_color)
            else resources.getColor(R.color.text_main_gray)
        )
        mBinding.longShortSwitchContainer.shortSelected.visibility = if (long)
            View.INVISIBLE else View.VISIBLE

        //更新底部parent fragment 底部item 变化
        val parentFragment = requireActivity().supportFragmentManager.findFragmentByTag(
            ContractDetailFragment.TAG
        )
        parentFragment?.apply {
            if (this is ContractDetailFragment) {
                refreshLongShortVisible(long)
            }
        }
    }

    fun commitOrder() {
        showToast("提交订单")
    }

    companion object {
        const val TAG = "CreateOrderFragment"
        fun create(): CreateOrderFragment {
            return CreateOrderFragment().apply {
            }
        }
    }
}