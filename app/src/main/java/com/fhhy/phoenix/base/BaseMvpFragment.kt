package com.fhhy.phoenix.base
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.fhhy.phoenix.dialog.LoadingDialog
import showToast

/**
 * AMBaseMvpFragment class
 *
 * @author hesanwei created on 2020/1/16
 *
 */
@Suppress("UNCHECKED_CAST")
abstract class BaseMvpFragment<in V : IView, P : IPresenter<V>> : BaseFragment(), IView {
    /**
     * Presenter
     */
    protected var mPresenter: P? = null

    protected abstract fun createPresenter(): P

    override fun initView(view: View) {
        mPresenter = createPresenter()
        mPresenter?.attachView(this as V)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mPresenter?.detachView()
        this.mPresenter = null
    }

    override fun showLoading() {
        LoadingDialog.show((activity as AppCompatActivity?)?.supportFragmentManager!!)
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