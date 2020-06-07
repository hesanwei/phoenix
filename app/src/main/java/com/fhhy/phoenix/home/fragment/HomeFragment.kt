package com.fhhy.phoenix.home.fragment

import android.view.View
import com.fhhy.phoenix.R
import com.fhhy.phoenix.base.BaseMvpFragment
import com.fhhy.phoenix.home.HomeContract
import com.fhhy.phoenix.home.presenter.HomePresenter
import com.fhhy.phoenix.mine.fragment.MineFragment

// Created by admin on 2020/6/7.
class HomeFragment: BaseMvpFragment<HomeContract.View,HomeContract.Presenter>() {
    override fun createPresenter(): HomeContract.Presenter = HomePresenter()

    override fun getLayoutId(): Int = R.layout.fragment_home

    override fun initView(view: View) {
        super.initView(view)

    }

    override fun lazyLoad() {
    }

    companion object {
        fun newInstance(): HomeFragment {
            return HomeFragment()
        }
    }
}