package com.fhhy.phoenix.mine.activity

import android.content.Intent
import android.view.View
import com.fhhy.phoenix.R
import com.fhhy.phoenix.base.BaseMvpActivity
import com.fhhy.phoenix.contractdetail.dialog.ShareLinkDialog
import com.fhhy.phoenix.dialog.PosterDialog
import com.fhhy.phoenix.dialog.QRCodeDialog
import com.fhhy.phoenix.mine.contract.InviteContract
import com.fhhy.phoenix.mine.presenter.InvitePresenter
import com.jaeger.library.StatusBarUtil
import copyText
import kotlinx.android.synthetic.main.activity_invite.*
import setViewClickListener

// Created by admin on 2020/7/12.
class InviteActivity : BaseMvpActivity<InviteContract.View, InviteContract.Presenter>(),
    InviteContract.View, View.OnClickListener {
    override fun createPresenter(): InviteContract.Presenter = InvitePresenter()

    override fun getLayoutId(): Int = R.layout.activity_invite

    override fun initView() {
        super.initView()
        StatusBarUtil.setTransparentForImageView(this, civAvatar)
        initTestData()
        setViewClickListener(
            this,
            btnBack,
            clInviteRecord,
            clMyLevel,
            tvPoster,
            tvInviteNow,
            flQRCode,
            clInviteCode
        )
    }

    private fun initTestData() {
        tvName.text = "彭于晏"
        tvYesterdayUSDTEarnings.text = "0.2"
        tvTotalUSDTEarnings.text = "0.5"
        tvYesterdayBTCEarnings.text = "0.2"
        tvTotalBTCEarnings.text = "0.5"
        tvInviteCode.text = "test"
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnBack -> finish()
            R.id.clInviteRecord -> {
                startActivity(Intent(this, InviteRecordActivity::class.java))
            }
            R.id.clMyLevel -> {

            }

            R.id.clInviteCode -> {
                tvInviteCode.text?.toString()?.copyText(this)
            }

            R.id.tvPoster -> {
                PosterDialog().show(supportFragmentManager)
            }

            R.id.tvInviteNow -> {
                ShareLinkDialog().show(supportFragmentManager)
            }

            R.id.flQRCode -> {
                QRCodeDialog().show(supportFragmentManager)
            }
        }
    }
}