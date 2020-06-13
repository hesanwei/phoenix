package com.fhhy.phoenix.login.fragment

import android.os.Bundle
import android.view.View
import com.fhhy.phoenix.R
import com.fhhy.phoenix.base.BaseMvpFragment
import com.fhhy.phoenix.login.LoginContract
import com.fhhy.phoenix.login.presenter.LoginPresenter
import com.jaeger.library.StatusBarUtil
import com.jakewharton.rxbinding4.view.clicks
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : BaseMvpFragment<LoginContract.View, LoginContract.Presenter>() {

    override fun createPresenter(): LoginContract.Presenter = LoginPresenter()

    override fun getLayoutId(): Int = R.layout.fragment_login

    override fun initView(view: View) {
        super.initView(view)
        StatusBarUtil.setTransparentForImageView(activity, btnBack)
        StatusBarUtil.setLightMode(activity)

        setupListeners()

    }

    private fun setupListeners() {
        val back = btnBack.clicks()
            .subscribe {
                requireActivity().onBackPressed()
            }

        mCompositeDisposable.add(back)
    }

    override fun lazyLoad() {
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