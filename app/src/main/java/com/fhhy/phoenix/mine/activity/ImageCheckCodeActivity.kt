package com.fhhy.phoenix.mine.activity

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
    override fun getLayoutId(): Int = R.layout.activity_image_check_code

    override fun initView() {
        super.initView()
        ImgCheckCodeDialog(object : ImgCheckCodeDialog.OnOkListener {
            override fun onOkClick(imgCheckCode: String) {
                    mPresenter?.requestUpdatePwdCheckCode(SPUtils.getString(SPKeyConstants.SP_KEY_USER_PHONE), imgCheckCode)
            }
        }).show(supportFragmentManager)
    }

    override fun createPresenter(): ImageCheckCodeContract.Presenter =ImageCheckCodePresenter()
    override fun requestUpdatePwdCheckCodeSuccess() {//请求验证码成功
           //跳到重置页
    }

}