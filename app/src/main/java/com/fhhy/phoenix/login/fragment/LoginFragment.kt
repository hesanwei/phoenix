package com.fhhy.phoenix.login.fragment

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.graphics.BitmapFactory
import android.os.Bundle
import android.text.InputType
import android.text.TextUtils
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.view.View
import android.view.animation.DecelerateInterpolator
import android.widget.ImageButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatImageButton
import androidx.core.animation.doOnEnd
import androidx.core.animation.doOnStart
import androidx.core.view.isVisible
import com.fhhy.phoenix.R
import com.fhhy.phoenix.base.BaseMvpFragment
import com.fhhy.phoenix.bean.LoginBean
import com.fhhy.phoenix.constants.SPKeyConstants
import com.fhhy.phoenix.dialog.ImgCheckCodeDialog
import com.fhhy.phoenix.http.RetrofitManager
import com.fhhy.phoenix.login.LoginContract
import com.fhhy.phoenix.login.State
import com.fhhy.phoenix.login.event.LoginSuccessEvent
import com.fhhy.phoenix.login.presenter.LoginPresenter
import com.fhhy.phoenix.utils.SPUtils
import com.jaeger.library.StatusBarUtil
import com.jakewharton.rxbinding4.view.clicks
import com.jakewharton.rxbinding4.widget.textChanges
import isMobile
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_login_state_forgot_pwd.*
import kotlinx.android.synthetic.main.fragment_login_state_login_sms.*
import kotlinx.android.synthetic.main.fragment_login_state_main.*
import kotlinx.android.synthetic.main.fragment_login_state_register_one.*
import kotlinx.android.synthetic.main.fragment_login_state_register_two.*
import noDoubleClick
import org.greenrobot.eventbus.EventBus
import showToast
import java.util.*

class LoginFragment : BaseMvpFragment<LoginContract.View, LoginContract.Presenter>(),
    LoginContract.View {

    private var currentState: State = State.LOGIN
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
                setButtonClickable(btnLoginReg, !it.isNullOrEmpty())
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
                    setButtonClickable(btnLogin, true)
                } else {
                    setButtonClickable(btnLogin, false)
                }
            }
        mCompositeDisposable.add(mobile)

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
        val toRegister = toRegister.noDoubleClick {
            toNextState(State.REGISTER_STEP_ONE)
        }
        mCompositeDisposable.add(toRegister)

        //登录按钮
        btnLogin.noDoubleClick {

            //TODO  显示图形验证码 然后再确认是否显示验证码页面
            val mobile = etMobile.text.toString()
            val pwd = etPwd.text.toString()
            if (!mobile.isMobile()) {
                showToast("手机号格式不对")
                return@noDoubleClick
            }

            if (TextUtils.isEmpty(pwd)) {
                showToast("密码不能为空")
                return@noDoubleClick
            }
            doLogin(mobile, pwd)
        }
    }

    /**
     * 登录
     */
    private fun doLogin(
        mobile: String,
        pwd: String,
        smsCheckCode: String? = ""
    ) {
        //请求登录接口
        mPresenter?.requestLogin(mobile, pwd, smsCheckCode)
    }


    /**
     * 注册
     */
    private fun doRegister(
        mobile: String,
        pwd: String,
        smsCheckCode: String? = "",
        invitation_code: String = ""
    ) {
        //请求注册接口
        mPresenter?.requestRegister(mobile, pwd, smsCheckCode, invitation_code)
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
                    setButtonClickable(btnNext, true)
                } else {
                    setButtonClickable(btnNext, false)
                }
            }
        mCompositeDisposable.add(mobile)

        val next = btnNext.noDoubleClick {
            //TODO  先图形验证再请求发送验证码接口 再跳转注册页面的第二步
            val mobile = etMobileReg.text.toString()
            val isMobile = mobile.isMobile()
            if (isMobile) {
                showImgCheckCodeDialog(mobile)
            } else {
                showToast("手机号格式错误")
            }
        }
        mCompositeDisposable.add(next)

        val back = backLogin.noDoubleClick {
            toPreState()
        }
        mCompositeDisposable.add(back)
    }

    private fun toNextState(nextState: State) {
        currentState = nextState
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
            ctvRegisterCountdown.start()
            etPwdReg.transformationMethod = PasswordTransformationMethod.getInstance()
            btnPwdEyeReg.setImageResource(R.drawable.ic_login_eye_open)

            etInviteCode.text?.clear()

            tvSmsCodeTip.text = String.format(
                context!!.resources.getString(R.string.check_code_send_to_mobile),
                etMobileReg.text.toString()
            )
            val smsCode = etSmsCode.textChanges()
                .subscribe {
                    setButtonClickable(btnLoginReg, !it.isNullOrEmpty())
                }
            mCompositeDisposable.add(smsCode)

            btnLoginReg.noDoubleClick {
                val smsCheckCode = etSmsCode.text.toString()
                if (TextUtils.isEmpty(smsCheckCode)) {
                    showToast("短信验证码不能为空")
                    return@noDoubleClick
                }
                val pwd = etPwdReg.text.toString()
                if (pwd.length > 20 || pwd.length < 6) {
                    showToast("请输入6～20位数字、字母密码")
                    return@noDoubleClick
                }
                val mobile = etMobileReg.text.toString()
                val inviteCode = etInviteCode.text.toString()
                doRegister(mobile, pwd, smsCheckCode, inviteCode)
            }
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
            ctvLoginCountdown.start()
            tvSmsCodeTipLogin.text = String.format(
                context!!.resources.getString(R.string.check_code_send_to_mobile),
                etMobile.text.toString()
            )
            val smsCode = etSmsLogin.textChanges()
                .subscribe {
                    setButtonClickable(btnLoginSms, !it.isNullOrEmpty())
                }
            mCompositeDisposable.add(smsCode)

            btnLoginSms.noDoubleClick {
                val smsCheckCode = etSmsLogin.text.toString()
                if (TextUtils.isEmpty(smsCheckCode)) {
                    showToast("短信验证码不能为空")
                    return@noDoubleClick
                }
                val mobile = etMobile.text.toString()
                val pwd = etPwd.text.toString()

                doLogin(mobile, pwd, smsCheckCode)
            }
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
                PropertyValuesHolder.ofFloat(View.TRANSLATION_X, 0f, -width * 1f),
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
                PropertyValuesHolder.ofFloat(View.TRANSLATION_X, 0f, width * 1f),
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
        currentState = preState
        animExit(currState, preState)
        if (preState == State.LOGIN) {
            etMobile.setText(currMobile)
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

    override fun requestCheckCodeSuccess() {
        showToast("验证码请求成功")
        when (currentState) {
            State.REGISTER_STEP_ONE -> {
                toNextState(State.REGISTER_STEP_TWO)
            }
            State.LOGIN -> {
                toNextState(State.LOGIN_SMS)
            }
        }
    }

    override fun requestLoginSuccess(mobile: String, loginBean: LoginBean?) {
        if (loginBean != null) {
            if ("1" == (loginBean.need_sms_code)) {
                showImgCheckCodeDialog(mobile)
            } else {
                SPUtils.setString(
                    SPKeyConstants.SP_KEY_TOKEN,
                    if (TextUtils.isEmpty(loginBean.info)) "" else loginBean.info!!
                )
                SPUtils.setBoolean(SPKeyConstants.SP_KEY_IS_LOGIN, true)
                EventBus.getDefault().post(LoginSuccessEvent())
                requireActivity().finish()
            }
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
     * 展示图形验证码
     */
    private fun showImgCheckCodeDialog(mobile: String) {
        ImgCheckCodeDialog(object : ImgCheckCodeDialog.OnOkListener {
            override fun onOkClick(imgCheckCode: String) {
                mPresenter?.requestCheckCode(mobile, imgCheckCode)
            }
        }).show(activity!!.supportFragmentManager)
    }
}