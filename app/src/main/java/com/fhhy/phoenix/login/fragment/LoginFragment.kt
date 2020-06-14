package com.fhhy.phoenix.login.fragment

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.os.Bundle
import android.text.InputType
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.view.View
import android.view.animation.DecelerateInterpolator
import android.widget.ImageButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.animation.doOnEnd
import androidx.core.animation.doOnStart
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
import noDoubleClick
import java.util.*

class LoginFragment : BaseMvpFragment<LoginContract.View, LoginContract.Presenter>() {

    private val currentState: State = State.LOGIN
    private var currMobile = ""
    private val stateStack: Stack<State> = Stack()
    private val stateMap: Map<State, View> by lazy {
        hashMapOf(
            State.LOGIN to stateLogin,
            State.LOGIN_SMS to stateLoginSms,
            State.REGISTER_STEP_ONE to stateRegisterStepOne,
            State.REGISTER_STEP_TWO to stateRegisterStepTwo,
            State.FORGOT_PWD to stateForgotPwd
        )
    }

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
        stateStack.push(State.LOGIN)
//        stateLogin.isVisible = currentState == State.LOGIN
//        stateLoginSms.isVisible = currentState == State.LOGIN_SMS
//        stateRegisterStepOne.isVisible = currentState == State.REGISTER_STEP_ONE
//        stateRegisterStepTwo.isVisible = currentState == State.REGISTER_STEP_TWO
//        stateForgotPwd.isVisible = currentState == State.FORGOT_PWD
    }

    private fun setupListeners() {
        val back = btnBack.clicks()
            .subscribe {
                toPreState()
            }

        mCompositeDisposable.add(back)

        initLoginViewAndListeners()
        initLoginViewAndSmsListeners()
        initRegisterOneViewAndListeners()
        initRegisterTwoViewAndListeners()
        initForgotPwdViewAndListeners()
    }

    private fun initForgotPwdViewAndListeners() {
        etPwdSetPwd.transformationMethod = PasswordTransformationMethod.getInstance()

        val sms = etSmsCodeForgotPwd.textChanges()
            .subscribe {

            }
        mCompositeDisposable.add(sms)

        val setPwd = etPwdSetPwd.textChanges()
            .subscribe {

            }
        mCompositeDisposable.add(setPwd)

        val eye = btnEyeForgotPwd.noDoubleClick {
            switchPwdMode(etPwdSetPwd, btnEyeForgotPwd)
        }
        mCompositeDisposable.add(eye)

        btnResetPwd.noDoubleClick {
            //TODO 验证验证码
            toPreState()
        }
    }

    private fun initRegisterTwoViewAndListeners() {

        etPwdReg.transformationMethod = PasswordTransformationMethod.getInstance()

        val sms = etSmsCode.textChanges()
            .subscribe {

            }
        mCompositeDisposable.add(sms)

        val setPwd = etPwdReg.textChanges()
            .subscribe {

            }
        mCompositeDisposable.add(setPwd)

        val eye = btnPwdEyeReg.noDoubleClick {
            switchPwdMode(etPwdReg, btnPwdEyeReg)
        }
        mCompositeDisposable.add(eye)

        //邀请码
        val inviteCode = etInviteCode.textChanges()
            .subscribe {
                //
            }
        mCompositeDisposable.add(inviteCode)

        val register = btnLoginReg.noDoubleClick {
            //TODO  注册完成
            requireActivity().finish()
        }
        mCompositeDisposable.add(register)

    }

    private fun initLoginViewAndSmsListeners() {
        //TODO 开始倒计时
        etSmsLogin.textChanges()
            .subscribe {
                //TODO 设置按钮是否可点击
            }

        btnLoginSms.noDoubleClick {
            //TODO 验证短信验证码 并结束流程
            requireActivity().finish()
        }
    }

    private fun initLoginViewAndListeners() {

        etPwd.transformationMethod = PasswordTransformationMethod.getInstance()

        val areaCodeClick = tvArea.noDoubleClick {
              //  TODO("添加跳转选择国家地区")
            }
        mCompositeDisposable.add(areaCodeClick)

        val mobile = etMobile.textChanges()
            .subscribe {
                if (it.isNullOrEmpty()) {
                    currMobile = ""
                }
                if (etMobile.isVisible && !it.isNullOrEmpty()) {
                    currMobile = it.toString()
                }
            }
        mCompositeDisposable.add(mobile)

        //登录密码
        val pwd = etPwd.textChanges()
            .subscribe {
                //TODO

            }
        mCompositeDisposable.add(pwd)

        //显示还是隐藏密码
        val eye = btnPwdEye.noDoubleClick {
                switchPwdMode(etPwd, btnPwdEye)
            }

        mCompositeDisposable.add(eye)

        //忘记密码
        val toForgotPwd = tvForgotPwd.noDoubleClick {
                toNextState(State.FORGOT_PWD)
            }
        mCompositeDisposable.add(toForgotPwd)

        //去注册
        val toRegister = toRegister.noDoubleClick{
            toNextState(State.REGISTER_STEP_ONE)
        }
        mCompositeDisposable.add(toRegister)

        //登录按钮
        btnLogin.noDoubleClick {
            //TODO  显示图形验证码 然后再确认是否显示验证码页面
            //test
            toNextState(State.LOGIN_SMS)
        }
    }

    private fun initRegisterOneViewAndListeners() {
        val areaCodeClick = tvAreaReg.noDoubleClick {
           // TODO("添加跳转选择国家地区")
        }
        mCompositeDisposable.add(areaCodeClick)

        val mobile = etMobileReg.textChanges()
            .subscribe {
                if (it.isNullOrEmpty()) {
                    currMobile = ""
                }
                if (etMobileReg.isVisible && !it.isNullOrEmpty()) {
                    currMobile = it.toString()
                }
            }
        mCompositeDisposable.add(mobile)

        val next = btnNext.noDoubleClick {
            //TODO  先图形验证再请求发送验证码接口 再跳转注册页面的第二步
            toNextState(State.REGISTER_STEP_TWO)
        }
        mCompositeDisposable.add(next)

        val back = backLogin.noDoubleClick {
            toPreState()
        }
        mCompositeDisposable.add(back)
    }

    private fun toNextState(nextState: State) {
        val preState = stateStack.peek()
        stateStack.push(nextState)
        animEnter(preState, nextState)
        //预置状态
        if (nextState == State.REGISTER_STEP_ONE) {
            etMobileReg.setText(currMobile)
        }

        if (nextState == State.REGISTER_STEP_TWO) {
            etSmsCode.text?.clear()
            etPwdReg.text?.clear()
            //TODO 重置倒计时
            etPwdReg.transformationMethod = PasswordTransformationMethod.getInstance()
            btnPwdEyeReg.setImageResource(R.drawable.ic_login_eye_open)

            etInviteCode.text?.clear()
        }

        if (nextState == State.FORGOT_PWD) {
            etSmsCodeForgotPwd.text?.clear()
            //TODO 重置倒计时
            etPwdSetPwd.text?.clear()
            etPwdSetPwd.transformationMethod = PasswordTransformationMethod.getInstance()
            btnEyeForgotPwd.setImageResource(R.drawable.ic_login_eye_open)

        }

        if (nextState == State.LOGIN_SMS) {
            etSmsLogin.text?.clear()
            //TODO 重置倒计时
        }
    }

    //转场动画  右进左出
    private fun animEnter(
        preState: State,
        nextState: State
    ) {
        val currView = stateMap[preState]
        val nextView = stateMap[nextState]
        currView?.apply {
            Log.d(TAG, "exitAnim: ")
            val exitAnim = ObjectAnimator.ofPropertyValuesHolder(
                this,
                PropertyValuesHolder.ofFloat(View.TRANSLATION_X, 0f, -width* 1f),
                PropertyValuesHolder.ofFloat(View.ALPHA, 1.0f, 0f)
            )
            exitAnim.duration = 300
            exitAnim.interpolator = DecelerateInterpolator()
            exitAnim.doOnEnd {
                isVisible = false
                alpha = 1.0f
                translationX = 0f
            }
            exitAnim.start()
        }

        nextView?.apply {
            Log.d(TAG, "enterAnim: ")
            val enterAnim = ObjectAnimator.ofPropertyValuesHolder(
                this,
                PropertyValuesHolder.ofFloat(View.TRANSLATION_X, width * 1f, 0f),
                PropertyValuesHolder.ofFloat(View.ALPHA, 0.2f, 1.0f)
            )
            enterAnim.duration = 300
            enterAnim.interpolator = DecelerateInterpolator()
            enterAnim.doOnStart {
                alpha = 0.2f
                translationX = 1f * width
                isVisible = true
            }
            enterAnim.start()
        }

    }

    //转场动画  左进右出
    private fun animExit(
        currState: State,
        preState: State
    ) {
        val currView = stateMap[currState]
        val preView = stateMap[preState]
        currView?.apply {
            val exitAnim = ObjectAnimator.ofPropertyValuesHolder(
                this,
                PropertyValuesHolder.ofFloat(View.TRANSLATION_X, 0f, width* 1f),
                PropertyValuesHolder.ofFloat(View.ALPHA, 1.0f, 0f)
            )
            exitAnim.duration = 300
            exitAnim.interpolator = DecelerateInterpolator()
            exitAnim.doOnEnd {
                isVisible = false
                alpha = 1.0f
                translationX = 0f
            }
//            exitAnim.startDelay = 200
            exitAnim.start()
        }

        preView?.apply {
            val enterAnim = ObjectAnimator.ofPropertyValuesHolder(
                this,
                PropertyValuesHolder.ofFloat(View.TRANSLATION_X, -width * 1f, 0f),
                PropertyValuesHolder.ofFloat(View.ALPHA, 0f, 1.0f)
            )
            enterAnim.duration = 300
            enterAnim.interpolator = DecelerateInterpolator()
            enterAnim.doOnStart {
                alpha = 0f
                translationX = 1f * width
                isVisible = true
            }
            enterAnim.start()
        }

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
            btnPwdEye.setImageResource(R.drawable.ic_login_eye_open)
            etPwd.transformationMethod = PasswordTransformationMethod.getInstance()
            //光标最后
            etPwd.setSelection(etPwd.text.toString().length)
        } else {
            btnPwdEye.setImageResource(R.drawable.ic_login_eye_close)
            etPwd.transformationMethod = HideReturnsTransformationMethod.getInstance()
            //光标最后
            etPwd.setSelection(etPwd.text.toString().length)
        }
    }

    private fun updateCurrAreaCode() {
        //TODO 更新国家电话前缀
        tvArea.text = ""
        tvAreaReg.text = ""
    }

    override fun lazyLoad() {
    }

    fun toPreState() {
        val currState = stateStack.pop()
        if (currState == State.LOGIN) {
            requireActivity().finish()
            return
        }
        val preState = stateStack.peek()
        animExit(currState, preState)
        if (preState == State.LOGIN) {
            etMobile.setText(currMobile)
            etPwd.transformationMethod = HideReturnsTransformationMethod.getInstance()
            btnPwdEye.setImageResource(R.drawable.ic_login_eye_open)
        }
    }

    companion object {
        const val TAG = "LoginFragment"
        fun create(): LoginFragment {
            return LoginFragment().apply {
                arguments = Bundle().apply {

                }
            }
        }
    }
}