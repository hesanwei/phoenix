package com.fhhy.phoenix.base

import com.fhhy.phoenix.dialog.LoadingDialog
import showToast

/**
 * AMBaseMvpActivity class
 *
 * @author hesanwei created on 2020/1/17
 *
 */
abstract class BaseMvpActivity<in V : IView, P : IPresenter<V>> : BaseActivity(), IView {

    /**
     * Presenter
     */
    protected var mPresenter: P? = null

    protected abstract fun createPresenter(): P

    override fun initView() {
        mPresenter = createPresenter()
        mPresenter?.attachView(this as V)
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter?.detachView()
        this.mPresenter = null
    }

    override fun showLoading() {
        LoadingDialog.show(this)
    }

    override fun hideLoading() {
        LoadingDialog.dismiss()
    }

    override fun showError(errorMsg: String?) {
        showToast(errorMsg)
    }

    override fun showMsg(msg: String?) {
        showToast(msg)
    }
}