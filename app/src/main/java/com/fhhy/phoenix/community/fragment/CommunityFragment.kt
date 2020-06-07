package com.fhhy.phoenix.community.fragment

import android.view.View
import com.fhhy.phoenix.R
import com.fhhy.phoenix.base.BaseMvpFragment
import com.fhhy.phoenix.community.CommunityContract
import com.fhhy.phoenix.community.presenter.CommunityPresenter
import com.fhhy.phoenix.mine.fragment.MineFragment

// Created by admin on 2020/6/7.
class CommunityFragment : BaseMvpFragment<CommunityContract.View, CommunityContract.Presenter>() {
    override fun createPresenter(): CommunityContract.Presenter = CommunityPresenter()

    override fun getLayoutId(): Int = R.layout.fragment_community

    override fun initView(view: View) {
        super.initView(view)

    }

    override fun lazyLoad() {
    }

    companion object {
        fun newInstance(): CommunityFragment {
            return CommunityFragment()
        }
    }
}