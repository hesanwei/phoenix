package com.fhhy.phoenix.mine.activity

import com.fhhy.phoenix.R
import com.fhhy.phoenix.base.BaseActivity
import com.fhhy.phoenix.dialog.ImgCheckCodeDialog

/**
 * Created by heCunCun on 2020/7/13
 */
class ImageCheckCodeActivity : BaseActivity() {
    override fun getLayoutId(): Int = R.layout.activity_image_check_code

    override fun initView() {
        ImgCheckCodeDialog(object : ImgCheckCodeDialog.OnOkListener {
            override fun onOkClick(imgCheckCode: String) {

            }
        }).show(supportFragmentManager)
    }

}