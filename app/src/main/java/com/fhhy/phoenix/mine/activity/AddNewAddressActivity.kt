package com.fhhy.phoenix.mine.activity

import android.content.Intent
import android.view.View
import com.fhhy.phoenix.R
import com.fhhy.phoenix.base.BaseMvpActivity
import com.fhhy.phoenix.mine.activity.FundsAccountDetailActivity.Companion.FUNDS_NAME
import com.fhhy.phoenix.mine.contract.AddNewAddressContract
import com.fhhy.phoenix.mine.presenter.AddNewsAddressPresenter
import com.fhhy.phoenix.utils.QRCodeUtils
import kotlinx.android.synthetic.main.activity_add_new_address.*
import setViewClickListener
import showToast

// Created by admin on 2020/6/21.
class AddNewAddressActivity :
    BaseMvpActivity<AddNewAddressContract.View, AddNewAddressContract.Presenter>(),
    AddNewAddressContract.View,
    View.OnClickListener {
    override fun createPresenter(): AddNewAddressContract.Presenter = AddNewsAddressPresenter()

    override fun getLayoutId(): Int = R.layout.activity_add_new_address

    override fun initView() {
        super.initView()
        initData()
        setViewClickListener(this, btnBack, tvAdd, ibScan)
    }

    private fun initData() {
        val fundsName = intent.getStringExtra(FUNDS_NAME)
        tvTitle.text = String.format(resources.getString(R.string.add_coin_address), fundsName)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnBack -> finish()

            R.id.tvAdd -> {

            }

            R.id.ibScan -> {
                QRCodeUtils.jumpToScanActivity(this, REQUEST_CODE, PERMISSION_CODE)
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_CODE) {
            QRCodeUtils.jumpToScanActivity(this, REQUEST_CODE, PERMISSION_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE) {
            if (data != null) {
                val result = data.getStringExtra("result")
                showToast("扫码结果=$result")
            }
        }
    }

    companion object {
        private const val REQUEST_CODE = 0x001
        private const val PERMISSION_CODE = 0x002
    }
}