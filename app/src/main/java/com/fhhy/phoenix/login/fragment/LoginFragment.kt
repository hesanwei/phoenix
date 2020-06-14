package com.fhhy.phoenix.login.fragment

import android.os.Bundle
import android.text.InputType
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.widget.ImageButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.view.isVisible
import com.fhhy.phoenix.R
import com.fhhy.phoenix.base.BaseMvpFragment
import com.fhhy.phoenix.login.LoginContract
import com.fhhy.phoenix.login.State
import com.fhhy.phoenix.login.presenter.LoginPresenter
import com.jaeger.library.StatusBarUtil
import com.jakewharton.rxbinding4.view.clicks
import com.jakewharton.rxbinding4.widget.textChanges
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_login_state_forgot_pwd.*
import kotlinx.android.synthetic.main.fragment_login_state_login_sms.*
import kotlinx.android.synthetic.main.fragment_login_state_main.*
import kotlinx.android.synthetic.main.fragment_login_state_register_one.*
import kotlinx.android.synthetic.main.fragment_login_state_register_two.*
import showToast

class LoginFragment : BaseMvpFragment<LoginContract.View, LoginContract.Presenter>() {

    private val currentState: State = State.LOGIN
    private var currMobile = ""

    override fun createPresenter(): LoginContract.Presenter = LoginPresenter()

    override fun getLayoutId(): Int = R.layout.fragment_login

    override fun initView(view: View) {
        super.initView(view)
        StatusBarUtil.setTransparentForImageView(activity, btnBack)
        StatusBarUtil.setLightMode(activity)

        showViewByState()
        setupListeners()
    }

    private fun showViewByState() {
        stateLogin.isVisible = currentState == State.LOGIN
        stateLoginSms.isVisible = currentState == State.LOGIN_SMS
        stateRegisterStepOne.isVisible = currentState == State.REGISTER_STEP_ONE
        stateRegisterStepTwo.isVisible = currentState == State.REGISTER_STEP_TWO
        stateForgotPwd.isVisible = currentState == State.FORGOT_PWD
    }

    private fun setupListeners() {
        val back = btnBack.clicks()
            .subscribe {
                requireActivity().onBackPressed()
            }

        mCompositeDisposable.add(back)

        initLoginListeners()
//        initLoginSmsListeners()
        initRegisterOneListeners()
//        initRegisterTwoListeners()
//        initForgotPwdListeners()
    }

    private fun initLoginListeners() {
        val areaCodeClick = tvArea.clicks()
            .subscribe {
                TODO("添加跳转选择国家地区")
            }
        mCompositeDisposable.add(areaCodeClick)

        val mobile = etMobile.textChanges()
            .subscribe {
                if (etMobile.isVisible && !it.isNullOrEmpty()) {
                    currMobile = it.toString()
                    showToast(currMobile)
                }
            }
        mCompositeDisposable.add(mobile)

        val pwd = etPwd.textChanges()
            .subscribe {

            }
        mCompositeDisposable.add(pwd)

        val eye = btnPwdEye.clicks()
            .subscribe {
                switchPwdMode(etPwd, btnPwdEye)
            }

        mCompositeDisposable.add(eye)
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
//        if (showPwd) {
//            showPwd = false
            //否则隐藏密码、
            etPwd.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
//            iv_eye.setImageResource(R.mipmap.icon_login_look)
//            et_pwd.transformationMethod = PasswordTransformationMethod.getInstance()
//            //光标最后
//            et_pwd.setSelection(et_pwd.text.toString().length)
//        } else {
//            showPwd = true
//            如果选中，显示密码
//            iv_eye.setImageResource(R.mipmap.icon_login_look_pre)
//            et_pwd.transformationMethod = HideReturnsTransformationMethod.getInstance()
            //光标最后
//            et_pwd.setSelection(et_pwd.text.toString().length)
//        }
    }

    private fun initRegisterOneListeners() {

    }

    private fun updateCurrAreaCode() {
        //TODO 更新国家电话前缀
        tvArea.text = ""
        tvAreaReg.text = ""
    }

    override fun lazyLoad() {
    }

    fun onBackPressed() {
       when(currentState) {
           State.LOGIN -> {
               requireActivity().finish()
           }
           State.REGISTER_STEP_ONE -> {

           }
           State.REGISTER_STEP_TWO -> {

           }
           State.FORGOT_PWD -> {

           }
       }

    }
    companion object {
        fun create(): LoginFragment {
            return LoginFragment().apply {
                arguments = Bundle().apply {

                }
            }
        }
    }
}