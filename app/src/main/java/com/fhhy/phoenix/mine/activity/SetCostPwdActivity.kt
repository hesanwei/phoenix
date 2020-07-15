package com.fhhy.phoenix.mine.activity

import android.text.InputType
import android.text.TextUtils
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.widget.ImageButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatImageButton
import com.fhhy.phoenix.R
import com.fhhy.phoenix.base.BaseMvpActivity
import com.fhhy.phoenix.mine.contract.SetCostPwdContract
import com.fhhy.phoenix.mine.presenter.SetCostPwdPresenter
import com.jakewharton.rxbinding4.widget.textChanges
import kotlinx.android.synthetic.main.acitivty_set_cost_pwd.*
import kotlinx.android.synthetic.main.activity_reset_login_pwd.btnResetPwd
import kotlinx.android.synthetic.main.fragment_login_state_forgot_pwd.*
import noDoubleClick
import setViewClickListener
import showToast

/**
 * Created by heCunCun on 2020/7/15
 */
class SetCostPwdActivity : BaseMvpActivity<SetCostPwdContract.View, SetCostPwdContract.Presenter>(),
    SetCostPwdContract.View,
    View.OnClickListener {
    override fun createPresenter(): SetCostPwdContract.Presenter = SetCostPwdPresenter()

    override fun getLayoutId(): Int = R.layout.acitivty_set_cost_pwd

    override fun initView() {
        super.initView()
        setViewClickListener(this, btnResetPwd,btnBack)
        val smsCodeForgotPwd = etCostPwd.textChanges()
            .subscribe {
                setButtonClickable(
                    btnResetPwd,
                    !it.isNullOrEmpty() && !TextUtils.isEmpty(etCostPwd2.text)
                )
            }

        val pwdSetPwd = etCostPwd2.textChanges()
            .subscribe {
                setButtonClickable(
                    btnResetPwd,
                    !it.isNullOrEmpty() && !TextUtils.isEmpty(etCostPwd.text)
                )
            }

        val eye1 = btnEyeForgotPwd1.noDoubleClick {
            switchPwdMode(etCostPwd, btnEyeForgotPwd1)
        }
        val eye2 = btnEyeForgotPwd2.noDoubleClick {
            switchPwdMode(etCostPwd2, btnEyeForgotPwd2)
        }
    }

    /**
     * 设置下一步按钮是否可点击
     */
    private fun setButtonClickable(button: AppCompatImageButton, isClickable: Boolean) {
        button.isClickable = isClickable
        button.setImageResource(if (isClickable) R.drawable.ic_login_next_enable else R.drawable.ic_login_next_disable)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnResetPwd -> {
                //开始下一步
                mPresenter?.requestSetCostPwd(etCostPwd.text.toString(), etCostPwd2.text.toString())
            }
            R.id.btnBack->{
                finish()
            }
        }
    }

    override fun requestSetCostPwdSuccess() {
        showToast("设置资金密码成功")
        finish()
    }

    /**
     * 显示密码操作
     */
    private fun switchPwdMode(
        etPwd: AppCompatEditText,
        btnPwdEye: ImageButton
    ) {
        if (etPwd.text.toString().isEmpty()) {
            return
        }
        //是否已经显示了
        val showPwd = etPwd.transformationMethod != PasswordTransformationMethod.getInstance()
        if (showPwd) {
            //否则隐藏密码、
            etPwd.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            btnPwdEye.setImageResource(R.drawable.ic_login_eye_close)
            etPwd.transformationMethod = PasswordTransformationMethod.getInstance()
            //光标最后
            etPwd.setSelection(etPwd.text.toString().length)
        } else {
            btnPwdEye.setImageResource(R.drawable.ic_login_eye_open)
            etPwd.transformationMethod = HideReturnsTransformationMethod.getInstance()
            //光标最后
            etPwd.setSelection(etPwd.text.toString().length)
        }
    }
}