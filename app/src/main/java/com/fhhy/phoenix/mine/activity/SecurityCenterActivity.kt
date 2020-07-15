package com.fhhy.phoenix.mine.activity

import android.content.Intent
import android.view.View
import com.fhhy.phoenix.R
import com.fhhy.phoenix.base.BaseActivity
import kotlinx.android.synthetic.main.activity_security_center.*
import setViewClickListener

/**
 * Created by heCunCun on 2020/7/13
 */
class SecurityCenterActivity : BaseActivity(), View.OnClickListener {
    override fun getLayoutId(): Int = R.layout.activity_security_center

    override fun initView() {
        setViewClickListener(this, btnBack, ll_login_pwd, ll_cost_pwd)
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
                //todo 已设置了资金密码  进入图形验证  然后进入重新设置
                val intent = Intent(this, ImageCheckCodeActivity::class.java)
                intent.putExtra("type","2")
                startActivity(intent)
                //todo 未设置密码 进入设置页面
//                val intent = Intent(this, SetCostPwdActivity::class.java)
//                startActivity(intent)
            }
        }
    }
}