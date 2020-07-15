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
import com.fhhy.phoenix.constants.SPKeyConstants
import com.fhhy.phoenix.mine.contract.ResetLoginPwdContract
import com.fhhy.phoenix.mine.presenter.ResetLoginPwdPresenter
import com.fhhy.phoenix.utils.SPUtils
import com.jakewharton.rxbinding4.widget.textChanges
import kotlinx.android.synthetic.main.activity_reset_login_pwd.*
import kotlinx.android.synthetic.main.activity_reset_login_pwd.btnEyeForgotPwd
import kotlinx.android.synthetic.main.activity_reset_login_pwd.btnResetPwd
import kotlinx.android.synthetic.main.activity_reset_login_pwd.etPwdSetPwd
import kotlinx.android.synthetic.main.activity_reset_login_pwd.etSmsCodeForgotPwd
import kotlinx.android.synthetic.main.activity_reset_login_pwd.tvSmsCodeTipForgotPwd
import kotlinx.android.synthetic.main.fragment_login_state_forgot_pwd.*
import noDoubleClick
import setViewClickListener
import showToast

/**
 * Created by heCunCun on 2020/7/15
 */
class ResetLoginPwdActivity:BaseMvpActivity<ResetLoginPwdContract.View,ResetLoginPwdContract.Presenter>(),ResetLoginPwdContract.View,
    View.OnClickListener {
    override fun createPresenter(): ResetLoginPwdContract.Presenter = ResetLoginPwdPresenter()

    override fun getLayoutId(): Int = R.layout.activity_reset_login_pwd

    override fun initView() {
        super.initView()
        val  mobile = SPUtils.getString(SPKeyConstants.SP_KEY_USER_PHONE)
        tvSmsCodeTipForgotPwd.text="验证码已发送至+86 $mobile"
        setViewClickListener(this,btnResetPwd,btnBack)
        val smsCodeForgotPwd = etSmsCodeForgotPwd.textChanges()
            .subscribe {
                setButtonClickable(
                    btnResetPwd,
                    !it.isNullOrEmpty() && !TextUtils.isEmpty(etPwdSetPwd.text)
                )
            }

        val pwdSetPwd = etPwdSetPwd.textChanges()
            .subscribe {
                setButtonClickable(
                    btnResetPwd,
                    !it.isNullOrEmpty() && !TextUtils.isEmpty(etSmsCodeForgotPwd.text)
                )
            }


        val eye = btnEyeForgotPwd.noDoubleClick {
            switchPwdMode(etPwdSetPwd, btnEyeForgotPwd)
        }
    }

    /**
     * 设置下一步按钮是否可点击
     */
    private fun setButtonClickable(button: AppCompatImageButton, isClickable: Boolean) {
        button.isClickable = isClickable
        button.setImageResource(if (isClickable) R.drawable.ic_login_next_enable else R.drawable.ic_login_next_disable)
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

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btnResetPwd->{
                val smsCheckCode = etSmsCodeForgotPwd.text.toString()
                if (TextUtils.isEmpty(smsCheckCode)) {
                    showToast("短信验证码不能为空")
                    return
                }
                val pwd = etPwdSetPwd.text.toString()
                if (pwd.length > 20 || pwd.length < 6) {
                    showToast("请输入6～20位数字、字母密码")
                    return
                }
                doUpdatePwd(smsCheckCode, pwd)
            }
            R.id.btnBack->{
                finish()
            }
        }

    }
    private fun doUpdatePwd(smsCheckCode: String?, pwd: String?) {
        mPresenter?.requestUpdatePwd(smsCheckCode, pwd)
    }
    override fun requestUpdatePwdSuccess() {
            finish()
    }
}