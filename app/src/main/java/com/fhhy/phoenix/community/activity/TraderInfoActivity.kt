package com.fhhy.phoenix.community.activity

import com.fhhy.phoenix.R
import com.fhhy.phoenix.base.BaseMvpActivity
import com.fhhy.phoenix.community.contract.TraderInfoContract
import com.fhhy.phoenix.community.presenter.TraderInfoPresenter
import com.fhhy.phoenix.contractdetail.dialog.ShareLinkDialog
import showToast

/**
 * Created by hecuncun on 2020/7/5
 */
class TraderInfoActivity:BaseMvpActivity<TraderInfoContract.View,TraderInfoContract.Presenter>(),TraderInfoContract.View {
    override fun createPresenter(): TraderInfoContract.Presenter =TraderInfoPresenter()

    override fun getLayoutId(): Int= R.layout.activity_trader_info

    override fun initView() {
        super.initView()
        ShareLinkDialog.newInstance().setOnConfirmListener(object :ShareLinkDialog.OnConfirmListener{
            override fun onConfirm() {
                showToast("分享链接")
            }

        }).show(supportFragmentManager,"share")
    }
}