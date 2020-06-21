package com.fhhy.phoenix.contractdetail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.fhhy.phoenix.base.BaseVBFragment
import com.fhhy.phoenix.databinding.FragmentContractDetailBinding
import com.github.fujianlian.klinechart.KLineChartAdapter
import com.github.fujianlian.klinechart.KLineEntity
import com.github.fujianlian.klinechart.formatter.DateFormatter
import io.reactivex.android.schedulers.AndroidSchedulers
import noDoubleClick

class ContractDetailFragment : BaseVBFragment<FragmentContractDetailBinding>() {

    private val viewModel: ContractDetailViewModel by lazy {
        ViewModelProvider(this, ContractDetailViewModel.Factory(requireActivity().application)).get(ContractDetailViewModel::class.java)
    }

    private val kLineAdapter by lazy { KLineChartAdapter() }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentContractDetailBinding =
        FragmentContractDetailBinding.inflate(inflater, container, false)

    override fun setupViews() {
        mBinding.kLineChartView.adapter = kLineAdapter
        mBinding.kLineChartView.dateTimeFormatter = DateFormatter()
        mBinding.kLineChartView.setGridRows(4)
        mBinding. kLineChartView.setGridColumns(4)

        mBinding.kLineChartView.justShowLoading()
    }

    override fun setupListeners() {
        mBinding.appBar.btnBack.noDoubleClick {
            requireActivity().finish()
        }

    }

    override fun setupObservers() {
        val subscribe = viewModel.kLineEntities
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext {
                setupKLineChartView(it)
            }.subscribe()
        mCompositeDisposable.add(subscribe)
    }

    private fun setupKLineChartView(entities: List<KLineEntity>) {
        Log.d(TAG, "setupKLineChartView: ")
        kLineAdapter.addFooterData(entities)
        kLineAdapter.notifyDataSetChanged()
        mBinding.kLineChartView.startAnimation()
        mBinding.kLineChartView.refreshEnd()
    }

    companion object {
        const val TAG = "LoginFragment"
        fun create(): ContractDetailFragment {
            return ContractDetailFragment().apply {
                arguments = Bundle().apply {

                }
            }
        }
    }
}