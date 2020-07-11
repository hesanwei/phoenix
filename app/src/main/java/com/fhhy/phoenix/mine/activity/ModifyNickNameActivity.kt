package com.fhhy.phoenix.mine.activity

import android.app.Activity
import android.content.Intent
import android.text.TextUtils
import com.fhhy.phoenix.R
import com.fhhy.phoenix.base.BaseActivity
import com.fhhy.phoenix.constants.SPKeyConstants
import kotlinx.android.synthetic.main.activity_modify_nick_name.*
import showToast

// Created by admin on 2020/7/12.
class ModifyNickNameActivity : BaseActivity() {
    override fun getLayoutId(): Int = R.layout.activity_modify_nick_name

    override fun initView() {
        tvCommit.setOnClickListener {
            val nickName = etNickName.text
            if (TextUtils.isEmpty(nickName)) {
                showToast("昵称不能为空")
                return@setOnClickListener
            }
            val intent = Intent()
            intent.putExtra(SPKeyConstants.SP_KEY_NICKNAME, nickName)
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
        btnBack.setOnClickListener { finish() }
    }
}