package com.fhhy.phoenix.mine.activity

import com.fhhy.phoenix.R
import com.fhhy.phoenix.base.BaseMvpActivity
import com.fhhy.phoenix.mine.contract.ImageCheckCodeContract
import com.fhhy.phoenix.mine.presenter.ImageCheckCodePresenter

/**
 * Created by heCunCun on 2020/7/13
 */
class ImageCheckCodeActivity : BaseMvpActivity<ImageCheckCodeContract.View,ImageCheckCodeContract.Presenter>(),ImageCheckCodeContract.View {
    override fun getLayoutId(): Int = R.layout.activity_image_check_code

    override fun initView() {
        super.initView()
    }

    override fun createPresenter(): ImageCheckCodeContract.Presenter =ImageCheckCodePresenter()

}