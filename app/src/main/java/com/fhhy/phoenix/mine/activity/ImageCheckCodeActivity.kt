package com.fhhy.phoenix.mine.activity

import android.content.DialogInterface
import android.content.Intent
import com.fhhy.phoenix.R
import com.fhhy.phoenix.base.BaseMvpActivity
import com.fhhy.phoenix.constants.SPKeyConstants
import com.fhhy.phoenix.dialog.ImgCheckCodeDialog
import com.fhhy.phoenix.mine.contract.ImageCheckCodeContract
import com.fhhy.phoenix.mine.presenter.ImageCheckCodePresenter
import com.fhhy.phoenix.utils.SPUtils


/**
 * Created by heCunCun on 2020/7/13
 */
class ImageCheckCodeActivity : BaseMvpActivity<ImageCheckCodeContract.View,ImageCheckCodeContract.Presenter>(),ImageCheckCodeContract.View {
    private   var imgCheckCodeDialog:ImgCheckCodeDialog?=null
    override fun getLayoutId(): Int = R.layout.activity_image_check_code

    override fun initView() {
        super.initView()
        val type = intent.getStringExtra("type")!!
        imgCheckCodeDialog = ImgCheckCodeDialog(object : ImgCheckCodeDialog.OnOkListener {
            override fun onOkClick(imgCheckCode: String) {
                if (type == "1") {
                    val mobile = SPUtils.getString(SPKeyConstants.SP_KEY_USER_PHONE)
                    mPresenter?.requestUpdatePwdCheckCode(mobile, imgCheckCode)
                } else {
                    mPresenter?.requestUpdateCostPwdCheckCode(imgCheckCode)
                }

            }
        })
        imgCheckCodeDialog?.setConfirmDismiss(false)
        imgCheckCodeDialog?.show(supportFragmentManager)
        imgCheckCodeDialog?.setOnDismissListener(DialogInterface.OnDismissListener {
            hideLoading()
            finish()
        })
    }

    override fun createPresenter(): ImageCheckCodeContract.Presenter =ImageCheckCodePresenter()
    override fun requestUpdatePwdCheckCodeSuccess() {//请求验证码成功
           //跳到重置登录密码页
        startActivity(Intent(this,ResetLoginPwdActivity::class.java))
    }

    override fun requestUpdateCostPwdCheckCodeSuccess() {
        //跳到重置登录资金密码页
         startActivity(Intent(this,ResetCostPwdActivity::class.java))
         finish()

    }

}