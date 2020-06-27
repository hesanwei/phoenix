package com.fhhy.phoenix.contractdetail.lastestdeal

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.entity.MultiItemEntity
import com.fhhy.phoenix.R
import com.fhhy.phoenix.base.BaseVBFragment
import com.fhhy.phoenix.contractdetail.contractinfo.ContractInfoAdapter
import com.fhhy.phoenix.contractdetail.dialog.MarketPriceDialog
import com.fhhy.phoenix.databinding.FragmentContractInfoBinding
import com.fhhy.phoenix.databinding.FragmentLatestDealBinding
import com.fhhy.phoenix.test.PriceSourceBean
import com.fhhy.phoenix.test.TradeInfoBean

class ContractInfoFragment : BaseVBFragment<FragmentContractInfoBinding>() {

    private val mAdapter: ContractInfoAdapter by lazy {
        ContractInfoAdapter()
    }

    private val marketPriceDialog: MarketPriceDialog by lazy {
        MarketPriceDialog.newInstance()
    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentContractInfoBinding =
        FragmentContractInfoBinding.inflate(inflater, container, false)

    override fun setupViews() {
        mBinding.rvContractInfo.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = mAdapter
        }

        mAdapter.addChildClickViewIds(R.id.title_price)
        mAdapter.setOnItemChildClickListener { adapter, view, position ->
            if (view.id == R.id.title_price) {
                marketPriceDialog.show(requireFragmentManager(), "marketPriceDialog")
            }
        }
    }

    override fun setupListeners() {

    }

    override fun setupObservers() {
        val data = arrayListOf<MultiItemEntity>()
        data.add(ContractInfoAdapter.TitlePriceItem())
        for (i in 1..3) {
            data.add(ContractInfoAdapter.NormalPriceItem(PriceSourceBean("HuoBi", "33%", "9978.1")))
        }
        data.add(ContractInfoAdapter.TitleTradeInfo())
        for (i in 1..3) {
            data.add(ContractInfoAdapter.NormalTradeInfo(TradeInfoBean("USDT", "做多500k USDT", "做空500k USDT", "125X")))
        }
        mAdapter.setList(data)
    }

    companion object {
        fun create(): ContractInfoFragment {
            return ContractInfoFragment()
        }
    }
}