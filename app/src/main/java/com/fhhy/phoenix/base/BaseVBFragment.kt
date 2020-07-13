package com.fhhy.phoenix.base

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.fhhy.phoenix.constants.SPKeyConstants
import com.fhhy.phoenix.dialog.LoadingDialog
import com.fhhy.phoenix.utils.SPUtils
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

typealias Block = () -> Disposable

abstract class BaseVBFragment<VB : ViewBinding> : Fragment() {

    private var _binding: VB? = null
    protected val mBinding get() = _binding!!
    protected val mCompositeDisposable = CompositeDisposable()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("BaseFragment", "onCreateView: ")
        _binding = getViewBinding(inflater, container)
        return mBinding.root
    }

    abstract fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): VB

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        setupListeners()
        setupObservers()
    }

    protected fun addDisposable(block: Block) {
        mCompositeDisposable.add(block.invoke())
    }

    protected abstract fun setupViews()
    protected abstract fun setupListeners()
    protected abstract fun setupObservers()

    @CallSuper
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        mCompositeDisposable.clear()
    }

    protected fun isLogin(): Boolean {
        return SPUtils.getBoolean(SPKeyConstants.SP_KEY_IS_LOGIN)
    }

    protected fun showLoading() {
        LoadingDialog.show(requireContext())
    }

    protected fun hideLoading() {
        LoadingDialog.dismiss()
    }
}

