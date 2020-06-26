package com.fhhy.phoenix.contractdetail.lastestdeal

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.fhhy.phoenix.R
import com.fhhy.phoenix.base.BaseVBFragment
import com.fhhy.phoenix.databinding.FragmentLatestDealBinding
import com.fhhy.phoenix.test.DealBean

class LatestDealFragment : BaseVBFragment<FragmentLatestDealBinding>() {

    private val dealAdapter : LatestDealAdapter by lazy {
        LatestDealAdapter()
    }
    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentLatestDealBinding =
        FragmentLatestDealBinding.inflate(inflater, container, false)

    override fun setupViews() {
        mBinding.rvDealList.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = dealAdapter
        }
        dealAdapter.addHeaderView(layoutInflater.inflate(R.layout.header_latest_deal, null, false))
    }

    override fun setupListeners() {
    }

    override fun setupObservers() {
        val testData = arrayListOf<DealBean>()
        for (i in 1..20) {
            testData.add(DealBean("22:49:53", true, 9462.69f-(i/100f), 0.520175f + i/100f))
        }
        dealAdapter.setList(testData)
    }

    companion object {
        fun create(): LatestDealFragment {
            return LatestDealFragment()
        }
    }
}