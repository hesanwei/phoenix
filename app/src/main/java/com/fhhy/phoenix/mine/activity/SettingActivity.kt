package com.fhhy.phoenix.mine.activity

import android.content.Intent
import android.view.View
import androidx.core.content.ContentProviderCompat.requireContext
import com.fhhy.phoenix.R
import com.fhhy.phoenix.base.BaseActivity
import com.fhhy.phoenix.base.BaseMvpActivity
import com.fhhy.phoenix.bean.AppVersionBean
import com.fhhy.phoenix.mine.contract.SettingContract
import com.fhhy.phoenix.mine.presenter.SettingPresenter
import com.fhhy.phoenix.utils.PackageUtils
import kotlinx.android.synthetic.main.activity_setting.*
import setViewClickListener

/**
 * Created by hecuncun on 2020/7/11
 */
class SettingActivity : BaseMvpActivity<SettingContract.View,SettingContract.Presenter>(),SettingContract.View,View.OnClickListener {
    override fun getLayoutId(): Int = R.layout.activity_setting

    override fun initView() {
        super.initView()
        setViewClickListener(
            this,
            ll_personal_info_setting,
            ll_app_version_update,
            ll_language,
            ll_advanced_setting,
            btnBack
        )
        mPresenter?.getAppVersion()
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
            R.id.btnBack->{
                finish()
            }
        }
    }

    override fun createPresenter(): SettingContract.Presenter=SettingPresenter()
    override fun getAppVersionSuccess(bean: AppVersionBean?) {
        tv_app_version.text=bean?.app_version
        if (PackageUtils.getVersionName(this) != bean?.app_version){//对比本地版本名称
            tv_red_dot.visibility=View.VISIBLE
        }else{
            tv_red_dot.visibility=View.GONE
        }

    }
}