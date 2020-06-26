package com.fhhy.phoenix.contractdetail

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.ViewModelProvider
import com.fhhy.phoenix.R
import com.fhhy.phoenix.base.BaseVBFragment
import com.fhhy.phoenix.contractdetail.adapter.ViewPagerAdapter
import com.fhhy.phoenix.contractdetail.delegate.DelegateListFragment
import com.fhhy.phoenix.contractdetail.lastestdeal.ContractInfoFragment
import com.fhhy.phoenix.contractdetail.lastestdeal.LatestDealFragment
import com.fhhy.phoenix.contractdetail.pop.QuotaPopWindow
import com.fhhy.phoenix.contractdetail.pop.TimePopWindow
import com.fhhy.phoenix.databinding.FragmentContractDetailBinding
import com.github.fujianlian.klinechart.KLineChartAdapter
import com.github.fujianlian.klinechart.KLineEntity
import com.github.fujianlian.klinechart.formatter.DateFormatter
import com.jaeger.library.StatusBarUtil
import dp
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.fragment_contract.*
import net.lucode.hackware.magicindicator.ViewPagerHelper
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView
import noDoubleClick


class ContractDetailFragment : BaseVBFragment<FragmentContractDetailBinding>() {

    private val viewModel: ContractDetailViewModel by lazy {
        ViewModelProvider(this, ContractDetailViewModel.Factory(requireActivity().application)).get(
            ContractDetailViewModel::class.java
        )
    }

    private val kLineAdapter by lazy { KLineChartAdapter() }

    private val quotaPopWindow: QuotaPopWindow by lazy {
        QuotaPopWindow(requireContext())
    }

    private val morePopWindow: TimePopWindow by lazy {
        TimePopWindow(requireContext())
    }

    private val commonNavigator: CommonNavigator by lazy {
        CommonNavigator(requireContext())
    }
    private val titles by lazy {
        listOf(
            resources.getString(R.string.latest_deal),
            resources.getString(R.string.delegate_list),
            resources.getString(R.string.contract_info)
        )
    }

    private val fragments by lazy {
        listOf(
            LatestDealFragment.create(),
            DelegateListFragment.create(),
            ContractInfoFragment.create()
        )
    }

    private val viewpagerAdapter: ViewPagerAdapter by lazy {
        ViewPagerAdapter(
            childFragmentManager,
            FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT,
            fragments
        )
    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentContractDetailBinding =
        FragmentContractDetailBinding.inflate(inflater, container, false)

    override fun setupViews() {
        StatusBarUtil.setTransparentForImageView(activity, mBinding.appBar.root)
        StatusBarUtil.setLightMode(activity)
        //kLineChartView
        mBinding.kLineChartView.adapter = kLineAdapter
        mBinding.kLineChartView.dateTimeFormatter = DateFormatter()
        mBinding.kLineChartView.setGridRows(4)
        mBinding.kLineChartView.setGridColumns(4)

        mBinding.kLineChartView.justShowLoading()


        //indicator And viewPager
        initViewPager()

    }

    private fun initViewPager() {
        commonNavigator.isAdjustMode = true
        commonNavigator.adapter = object : CommonNavigatorAdapter() {
            override fun getTitleView(context: Context?, index: Int): IPagerTitleView {
                val colorTransitionPagerTitleView =
                    ColorTransitionPagerTitleView(context)
                colorTransitionPagerTitleView.normalColor =
                    resources.getColor(R.color.color_title_normal)
                colorTransitionPagerTitleView.selectedColor =
                    resources.getColor(R.color.color_title_selected)
                colorTransitionPagerTitleView.text = titles[index]
                colorTransitionPagerTitleView.setOnClickListener {
                    mBinding.viewPager.setCurrentItem(index, false)
                }
                return colorTransitionPagerTitleView
            }

            override fun getCount(): Int = titles.size

            override fun getIndicator(context: Context?): IPagerIndicator {
                val indicator = LinePagerIndicator(context)
                indicator.mode = LinePagerIndicator.MODE_EXACTLY
                indicator.roundRadius = 2f.dp
                indicator.lineWidth = 24f.dp
                indicator.lineHeight = 2f.dp
                return indicator
            }

        }
        mBinding.magicIndicator.navigator = commonNavigator

        mBinding.viewPager.offscreenPageLimit = 2
        mBinding.viewPager.adapter = viewpagerAdapter
        ViewPagerHelper.bind(mBinding.magicIndicator, mBinding.viewPager);
    }

    override fun setupListeners() {
        mBinding.appBar.btnBack.noDoubleClick {
            requireActivity().finish()
        }

        mBinding.optionGroup.quota.noDoubleClick {
            quotaPopWindow.showPopupWindow(mBinding.optionGroup.quota)
        }
        mBinding.optionGroup.more.noDoubleClick {

            morePopWindow
                .setOffsetX(mBinding.optionGroup.more.measuredWidth - 88.dp)
                .showPopupWindow(mBinding.optionGroup.more)
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