package com.fhhy.phoenix.mine.activity

import android.content.Intent
import android.text.TextUtils
import android.util.Base64
import android.view.View
import com.fhhy.phoenix.R
import com.fhhy.phoenix.base.BaseMvpActivity
import com.fhhy.phoenix.bean.AppVersionBean
import com.fhhy.phoenix.constants.SPKeyConstants
import com.fhhy.phoenix.dialog.LogoutDialog
import com.fhhy.phoenix.event.LoginSuccessEvent
import com.fhhy.phoenix.login.LoginActivity
import com.fhhy.phoenix.mine.contract.SettingContract
import com.fhhy.phoenix.mine.presenter.SettingPresenter
import com.fhhy.phoenix.utils.PackageUtils
import com.fhhy.phoenix.utils.SPUtils
import kotlinx.android.synthetic.main.activity_setting.*
import kotlinx.android.synthetic.main.item_bonus.*
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import setViewClickListener
import java.util.Base64.getDecoder

/**
 * Created by hecuncun on 2020/7/11
 */
class SettingActivity : BaseMvpActivity<SettingContract.View, SettingContract.Presenter>(),
    SettingContract.View, View.OnClickListener {
    override fun getLayoutId(): Int = R.layout.activity_setting

    override fun initView() {
        super.initView()
        setViewClickListener(
            this,
            ll_personal_info_setting,
            ll_app_version_update,
            ll_language,
            ll_advanced_setting,
            btnBack,
            tvLogout
        )
        mPresenter?.getAppVersion()
        tvLogout.visibility = if (isLogin()) View.VISIBLE else View.GONE
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.ll_personal_info_setting -> {//个人信息设置
                if (isLogin()) {
                    startActivity(Intent(this, PersonalInfoActivity::class.java))
                } else {
                    startActivity(Intent(this, LoginActivity::class.java))
                }
            }
            R.id.ll_app_version_update -> {//版本更新

            }
            R.id.ll_language -> {//语言

            }
            R.id.ll_advanced_setting -> {//高级设置

            }
            R.id.btnBack -> {
                finish()
            }

            R.id.tvLogout -> {
                LogoutDialog(View.OnClickListener { logout() }).show(supportFragmentManager)
            }
        }
    }

    private fun logout() {
        val token = SPUtils.getString(SPKeyConstants.SP_KEY_TOKEN)
        val base64 = String(Base64.decode(token, Base64.DEFAULT))
        if (!TextUtils.isEmpty(base64)) {
            // TODO: 2020/7/19  获取refresh_token
            val split = base64.split(Regex(":"))
            mPresenter?.requestLogout(split[split.size - 1])

        }
    }

    override fun createPresenter(): SettingContract.Presenter = SettingPresenter()
    override fun getAppVersionSuccess(bean: AppVersionBean?) {
        tv_app_version.text = bean?.app_version
        if (PackageUtils.getVersionName(this) != bean?.app_version) {//对比本地版本名称
            tv_red_dot.visibility = View.VISIBLE
        } else {
            tv_red_dot.visibility = View.GONE
        }

    }

    override fun requestLogoutSuccess() {
        tvLogout.visibility = View.GONE
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onLoginSuccess(event: LoginSuccessEvent) {
        tvLogout.visibility = View.VISIBLE
    }
}