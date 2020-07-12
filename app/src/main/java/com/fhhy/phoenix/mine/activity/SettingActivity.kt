package com.fhhy.phoenix.mine.activity

import android.content.Intent
import android.view.View
import androidx.core.content.ContentProviderCompat.requireContext
import com.fhhy.phoenix.R
import com.fhhy.phoenix.base.BaseActivity
import kotlinx.android.synthetic.main.activity_setting.*
import setViewClickListener

/**
 * Created by hecuncun on 2020/7/11
 */
class SettingActivity : BaseActivity(), View.OnClickListener {
    override fun getLayoutId(): Int = R.layout.activity_setting

    override fun initView() {
        setViewClickListener(
            this,
            ll_personal_info_setting,
            ll_app_version_update,
            ll_language,
            ll_advanced_setting
        )
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.ll_personal_info_setting -> {//个人信息设置
                startActivity(Intent(this, PersonalInfoActivity::class.java))
            }
            R.id.ll_app_version_update -> {//版本更新

            }
            R.id.ll_language -> {//语言

            }
            R.id.ll_advanced_setting -> {//高级设置

            }
        }
    }
}