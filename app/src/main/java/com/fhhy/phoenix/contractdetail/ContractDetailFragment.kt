package com.fhhy.phoenix.contractdetail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.ViewModelProvider
import com.fhhy.phoenix.R
import com.fhhy.phoenix.base.BaseVBFragment
import com.fhhy.phoenix.contract.activity.OrderActivity
import com.fhhy.phoenix.contractdetail.adapter.ViewPagerAdapter
import com.fhhy.phoenix.contractdetail.createorder.CreateOrderFragment
import com.fhhy.phoenix.contractdetail.delegate.DelegateListFragment
import com.fhhy.phoenix.contractdetail.dialog.*
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
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import net.lucode.hackware.magicindicator.ViewPagerHelper
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView
import noDoubleClick
import showToast
import underline


class ContractDetailFragment : BaseVBFragment<FragmentContractDetailBinding>() {

    private var isNormal: Boolean = true
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
    private val createOrderFragment by lazy {
        CreateOrderFragment.create()
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

    //资金费率dialog
    private val capitalRateDialog: CapitalRateDialog by lazy {
        CapitalRateDialog.newInstance()
    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentContractDetailBinding =
        FragmentContractDetailBinding.inflate(inflater, container, false)

    override fun setupViews() {
        StatusBarUtil.setTransparentForImageView(activity, mBinding.appBar.root)
        StatusBarUtil.setLightMode(activity)

        mBinding.head.fundRate.underline()

        //kLineChartView
        mBinding.kLineChartView.adapter = kLineAdapter
        mBinding.kLineChartView.dateTimeFormatter = DateFormatter()
        mBinding.kLineChartView.setGridRows(4)
        mBinding.kLineChartView.setGridColumns(4)

        mBinding.kLineChartView.justShowLoading()


        mBinding.kLineChartViewTwo.adapter = kLineAdapter
        mBinding.kLineChartViewTwo.dateTimeFormatter = DateFormatter()
        mBinding.kLineChartViewTwo.setGridRows(4)
        mBinding.kLineChartViewTwo.setGridColumns(4)

        mBinding.kLineChartViewTwo.justShowLoading()
        //replace some fragment
        childFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container, createOrderFragment, CreateOrderFragment.TAG)
            commit()
        }
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
        addDisposable {
            mBinding.appBar.btnBack.noDoubleClick {
                requireActivity().finish()
            }
        }

        addDisposable {
            mBinding.optionGroup.quota.noDoubleClick {
                quotaPopWindow.showPopupWindow(mBinding.optionGroup.quota)
            }
        }

        addDisposable {
            mBinding.optionGroup.more.noDoubleClick {
                morePopWindow
                    .setOffsetX(mBinding.optionGroup.more.measuredWidth - 88.dp)
                    .showPopupWindow(mBinding.optionGroup.more)
            }
        }
        addDisposable {
            mBinding.bottom.btnLongBullish.noDoubleClick {
                if (isNormal) {
                    hideLongOrShort(true)
                    switchMode()
                } else {
                    commitOrder()
                }
            }
        }

        addDisposable {
            mBinding.bottom.btnShortBearish.noDoubleClick {
                if (isNormal) {
                    hideLongOrShort(false)
                    switchMode()
                } else {
                    commitOrder()
                }
            }
        }

        addDisposable {
            mBinding.bottom.btnOrder.noDoubleClick {
                startActivity(Intent(context, OrderActivity::class.java))
            }
        }

        addDisposable {
            mBinding.head.fundRate.noDoubleClick {
                capitalRateDialog.show(requireActivity().supportFragmentManager, "capitalRate")
            }
        }
        addDisposable {//todo 测试dialog
            mBinding.head.currencyTrendPercent.noDoubleClick {
//                ConfirmOrderDialog.newInstance("做多","2X","市价","100VST","20","10")
//                    .setOnConfirmListener(object :ConfirmOrderDialog.OnConfirmListener{
//                        override fun onConfirm() {
//                           showToast("确认")
//                        }
//
//                    })
//                    .show(requireActivity().supportFragmentManager,"confirmOrder")

                StopProfitLossRateDialog.newInstance().setOnConfirmListener(object :StopProfitLossRateDialog.OnConfirmListener{
                    override fun onConfirm() {

                    }

                }).show(requireFragmentManager(),"stopRate")
            }
        }

    }

    fun switchMode() {
        isNormal = isNormal.not()
        mBinding.head.root.isVisible = isNormal
        mBinding.topDivider.isVisible = isNormal
//        mBinding.secondDivider.isVisible = isNormal
        mBinding.scrollContainer.isVisible = isNormal
        mBinding.llContainer.isVisible = !isNormal
        if (isNormal) {
            mBinding.bottom.btnLongBullish.isVisible = true
            mBinding.bottom.btnShortBearish.isVisible = true
        }
    }

    private fun hideLongOrShort(showLong: Boolean) {
        val fragment = childFragmentManager.findFragmentByTag(CreateOrderFragment.TAG)
        fragment?.run {
            if (this is CreateOrderFragment) {
                switchLongOrShortTab(showLong)
            }
        }
    }

    fun refreshLongShortVisible(showLong: Boolean) {
        mBinding.bottom.btnLongBullish.isVisible = showLong
        mBinding.bottom.btnShortBearish.isVisible = !showLong
    }

    private fun commitOrder() {
        val fragment = childFragmentManager.findFragmentByTag(CreateOrderFragment.TAG)
        fragment?.run {
            if (this is CreateOrderFragment) {
                commitOrder()
            }
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

        mBinding.kLineChartViewTwo.startAnimation()
        mBinding.kLineChartViewTwo.refreshEnd()
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