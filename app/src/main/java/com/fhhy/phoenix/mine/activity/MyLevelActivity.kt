package com.fhhy.phoenix.mine.activity

import com.fhhy.phoenix.R
import com.fhhy.phoenix.base.BaseMvpActivity
import com.fhhy.phoenix.mine.contract.MyLevelContract
import com.fhhy.phoenix.mine.presenter.MyLevelPresenter
import com.jaeger.library.StatusBarUtil
import kotlinx.android.synthetic.main.activity_my_level.*

// Created by admin on 2020/7/12.
class MyLevelActivity : BaseMvpActivity<MyLevelContract.View, MyLevelContract.Presenter>(),
    MyLevelContract.View {
    override fun createPresenter(): MyLevelContract.Presenter = MyLevelPresenter()

    override fun getLayoutId(): Int = R.layout.activity_my_level

    override fun initView() {
        super.initView()
        StatusBarUtil.setTransparentForImageView(this, tvTitle)
    }
}