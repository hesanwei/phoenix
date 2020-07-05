package com.fhhy.phoenix.community.fragment

import android.view.View
import androidx.fragment.app.Fragment
import com.fhhy.phoenix.R
import com.fhhy.phoenix.base.BaseMvpFragment
import com.fhhy.phoenix.community.contract.CommunityContract
import com.fhhy.phoenix.community.presenter.CommunityPresenter
import com.jaeger.library.StatusBarUtil
import kotlinx.android.synthetic.main.activity_order.*
import kotlinx.android.synthetic.main.activity_order.radioGroup
import kotlinx.android.synthetic.main.fragment_community.*

// Created by admin on 2020/6/7.
class CommunityFragment : BaseMvpFragment<CommunityContract.View, CommunityContract.Presenter>() {
    private val informationFragment by lazy {
        InformationFragment.newInstance()
    }

    private val followOrderFragment by lazy {
       FollowOrderFragment.newInstance()
    }

    override fun createPresenter(): CommunityContract.Presenter = CommunityPresenter()

    override fun getLayoutId(): Int = R.layout.fragment_community

    override fun initView(view: View) {
        super.initView(view)
        StatusBarUtil.setTransparentForImageView(activity,radioGroup)
        StatusBarUtil.setLightMode(activity)
        initRadioGroup()
    }

    override fun lazyLoad() {

    }
    private fun initRadioGroup() {
        replaceFragment(informationFragment)
        radioGroup.check(R.id.rg_information)
        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.rg_information -> replaceFragment(informationFragment)
                R.id.rg_follow_order -> replaceFragment(followOrderFragment)
            }
        }
    }
    private fun replaceFragment(fragment: Fragment) {
        val beginTransaction = requireActivity().supportFragmentManager.beginTransaction()
        beginTransaction.replace(R.id.flContainer, fragment)
        beginTransaction.commit()
    }
    companion object {
        fun newInstance(): CommunityFragment {
            return CommunityFragment()
        }
    }
}