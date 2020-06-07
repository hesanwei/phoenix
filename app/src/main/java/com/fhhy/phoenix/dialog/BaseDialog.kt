package com.fhhy.phoenix.dialog

import android.os.Bundle
import android.view.*
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.fhhy.phoenix.R

/**
 * BaseDialog class
 *
 * @author hesanwei created on 2020/1/10
 *
 */
abstract class BaseDialog : DialogFragment() {

    private val TAG = "base_dialog"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, R.style.BottomDialog)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)
        return inflater.inflate(getLayoutId(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()

    }

    override fun onStart() {
        super.onStart()
        val window = dialog?.window
        val params = window?.attributes
//        params.dimAmount = getDimAmount()
        params?.width = WindowManager.LayoutParams.MATCH_PARENT
        params?.height = WindowManager.LayoutParams.WRAP_CONTENT
        params?.gravity = getGravity()

        window?.attributes = params
    }

    open fun getGravity(): Int? {
        return Gravity.BOTTOM
    }

    fun show(manager: FragmentManager) {
        super.show(manager, TAG)
    }

    abstract fun getLayoutId(): Int
    abstract fun initView()

}