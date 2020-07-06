package com.fhhy.phoenix.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.fhhy.phoenix.constants.SPKeyConstants
import com.fhhy.phoenix.utils.SPUtils
import io.reactivex.rxjava3.disposables.CompositeDisposable
import org.greenrobot.eventbus.EventBus

/**
 * AMBaseFragment class
 *
 * @author hesanwei created on 2020/1/14
 *
 */
abstract class BaseFragment : Fragment() {
    /**
     * 视图是否加载完毕
     */
    private var isViewPrepare = false

    /**
     * 数据是否加载过了
     */
    private var hasLoadData = false

    /**
     * 加载布局
     */
    abstract fun getLayoutId(): Int

    /**
     * 初始化 View
     */
    abstract fun initView(view: View)

    /**
     * 懒加载
     */
    abstract fun lazyLoad()

    /**
     * 是否使用 EventBus
     */
    open fun useEventBus(): Boolean = false

    protected val mCompositeDisposable: CompositeDisposable by lazy {
        CompositeDisposable()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(getLayoutId(), null)
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser) {
            lazyLoadDataIfPrepared()
            visibleToUser()
        }
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (!hidden) {
            visibleToUser()
        }
    }

    open fun visibleToUser() {

    }

    protected fun isLogin(): Boolean {
        return SPUtils.getBoolean(SPKeyConstants.SP_KEY_IS_LOGIN)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (useEventBus()) {
            EventBus.getDefault().register(this)
        }
        isViewPrepare = true
        initView(view)
        lazyLoadDataIfPrepared()
    }

    open fun lazyLoadDataIfPrepared() {
        if (userVisibleHint && isViewPrepare && !hasLoadData) {
            lazyLoad()
            hasLoadData = true
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mCompositeDisposable.clear()
        if (useEventBus()) {
            EventBus.getDefault().unregister(this)
        }
    }
}