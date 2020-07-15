package com.fhhy.phoenix.mine.activity

import android.content.Intent
import android.view.View
import com.fhhy.phoenix.R
import com.fhhy.phoenix.base.BaseActivity
import com.fhhy.phoenix.base.BaseMvpActivity
import com.fhhy.phoenix.bean.SecuritySettingStateBean
import com.fhhy.phoenix.mine.contract.SecurityCenterContract
import com.fhhy.phoenix.mine.presenter.SecurityCenterPresenter
import kotlinx.android.synthetic.main.activity_security_center.*
import setViewClickListener

/**
 * Created by heCunCun on 2020/7/13
 */
class SecurityCenterActivity : BaseMvpActivity<SecurityCenterContract.View,SecurityCenterContract.Presenter>(),SecurityCenterContract.View, View.OnClickListener {
    override fun getLayoutId(): Int = R.layout.activity_security_center

    override fun initView() {
        super.initView()
        setViewClickListener(this, btnBack, ll_login_pwd, ll_cost_pwd)
        mPresenter?.getSecuritySettingState()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnBack -> {
                finish()
            }
            R.id.ll_login_pwd -> {
                val intent = Intent(this, ImageCheckCodeActivity::class.java)
                intent.putExtra("type","1")
                startActivity(intent)
            }
            R.id.ll_cost_pwd -> {
                //已设置了资金密码  进入图形验证  然后进入重新设置
                if (hasSetCostPwd==1){
                    val intent = Intent(this, ImageCheckCodeActivity::class.java)
                    intent.putExtra("type","2")
                    startActivity(intent)
                }else{
                    // 未设置密码 进入设置页面
                val intent = Intent(this, SetCostPwdActivity::class.java)
                startActivity(intent)
                }


            }
        }
    }
     private var hasSetCostPwd=0  //1已设置 0未设置
    override fun createPresenter(): SecurityCenterContract.Presenter = SecurityCenterPresenter()
    override fun getSecuritySettingStateSuccess(bean: SecuritySettingStateBean?) {
           hasSetCostPwd=bean?.login_password_status?:0
        if (bean?.login_password_status==0){
            tv_cost_pwd_set.text="立即设置"
        }else{
            tv_cost_pwd_set.text="重置"
        }
    }
}