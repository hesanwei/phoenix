package com.fhhy.phoenix.base
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.fhhy.phoenix.dialog.LoadingDialog
import io.reactivex.disposables.CompositeDisposable
import showToast

/**
 * AMBaseMvpFragment class
 *
 */
@Suppress("UNCHECKED_CAST")
abstract class BaseMvpFragment<in V : IView, P : IPresenter<V>> : BaseFragment(), IView {
    /**
     * Presenter
     */
    protected var mPresenter: P? = null

    protected val mDisposable by lazy {
        CompositeDisposable()
    }
    protected abstract fun createPresenter(): P

    override fun initView(view: View) {
        mPresenter = createPresenter()
        mPresenter?.attachView(this as V)
    }

    protected fun addDisposable(block: Block) {
        mCompositeDisposable.add(block.invoke())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mDisposable.clear()
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