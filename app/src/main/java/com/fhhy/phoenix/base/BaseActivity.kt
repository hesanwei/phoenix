package com.fhhy.phoenix.base

import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.WindowManager
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.fhhy.phoenix.R
import com.fhhy.phoenix.event.EmptyEvent
import com.jaeger.library.StatusBarUtil
import me.jessyan.autosize.internal.CustomAdapt
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.util.ArrayList

/**
 * BaseActivity class
 */
abstract class BaseActivity : AppCompatActivity(), CustomAdapt {
    /**
     * 是否使用 EventBus
     */
    open fun useEventBus(): Boolean = true

    /**
     * 是否设置状态栏颜色为白色
     */
    open fun setStatusBar(): Boolean = true

    /**
     * 布局文件id
     */
    abstract fun getLayoutId(): Int


    /**
     * 初始化 View
     */
    abstract fun initView()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN or WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)//软键盘不弹出，在setContentView()之前执行
        window.decorView.setBackgroundColor(resources.getColor(R.color.global_bg))
        setContentView(getLayoutId())
        if (useEventBus()) {
            EventBus.getDefault().register(this)
        }
        if (setStatusBar()) {
            StatusBarUtil.setColor(this, Color.WHITE, 0)
            StatusBarUtil.setLightMode(this)
        }
        initView()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (useEventBus()) {
            EventBus.getDefault().unregister(this)
        }
    }

    override fun isBaseOnWidth(): Boolean = true

    override fun getSizeInDp(): Float = 360f

    @Subscribe(threadMode = ThreadMode.MAIN)
    open fun onReceiveEvent(event: EmptyEvent) {

    }

    //权限相关
    private val TAG = "PermissionsUtil"
    private var REQUEST_CODE_PERMISSION = 0x00099
    /**
     * 请求权限
     *
     *
     * 警告：此处除了用户拒绝外，唯一可能出现无法获取权限或失败的情况是在AndroidManifest.xml中未声明权限信息
     * Android6.0+即便需要动态请求权限（重点）但不代表着不需要在AndroidManifest.xml中进行声明。
     *
     * @param permissions 请求的权限
     * @param requestCode 请求权限的请求码
     */
    fun requestPermission(permissions: Array<String>, requestCode: Int) {
        this.REQUEST_CODE_PERMISSION = requestCode
        if (checkPermissions(permissions)) {
            permissionSuccess(REQUEST_CODE_PERMISSION)
        } else {
            val needPermissions = getDeniedPermissions(permissions)
            ActivityCompat.requestPermissions(this, needPermissions.toTypedArray(), REQUEST_CODE_PERMISSION)
        }
    }

    /**
     * 检测所有的权限是否都已授权
     *
     * @param permissions
     * @return
     */
    fun checkPermissions(permissions: Array<String>): Boolean {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true
        }
        for (permission in permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                return false
            }
        }
        return true
    }

    /**
     * 获取权限集中需要申请权限的列表
     *
     * @param permissions
     * @return
     */
    private fun getDeniedPermissions(permissions: Array<String>): List<String> {
        val needRequestPermissionList = ArrayList<String>()
        for (permission in permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED || ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
                needRequestPermissionList.add(permission)
            }
        }
        return needRequestPermissionList
    }


    /**
     * 系统请求权限回调
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_PERMISSION) {
            if (verifyPermissions(grantResults)) {
                permissionSuccess(REQUEST_CODE_PERMISSION)
            } else {
                permissionFail(REQUEST_CODE_PERMISSION)
                showTipsDialog()
            }
        }
    }

    /**
     * 确认所有的权限是否都已授权
     *
     * @param grantResults
     * @return
     */
    private fun verifyPermissions(grantResults: IntArray): Boolean {
        for (grantResult in grantResults) {
            if (grantResult != PackageManager.PERMISSION_GRANTED) {
                return false
            }
        }
        return true
    }

    /**
     * 显示提示对话框
     */
    fun showTipsDialog() {
        AlertDialog.Builder(this)
            .setTitle("警告")
            .setMessage("需要必要的权限才可以正常使用该功能，您已拒绝获得该权限。\n" +
                    "如果需要重新授权，您可以点击“确定”按钮进入系统设置进行授权")
            .setNegativeButton("取消") { dialog, i ->
                dialog.dismiss()
            }
            .setPositiveButton("确定") { dialogInterface, i ->
                startAppSettings()
            }
            .show()
    }

    /**
     * 权限获取失败
     *
     * @param requestCode
     */
    fun permissionFail(requestCode: Int) {
        Log.d(TAG, "获取权限失败=$requestCode")
    }

    /**
     * 启动当前应用设置页面
     */
    private fun startAppSettings() {
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
        intent.data = Uri.parse("package:$packageName")
        startActivity(intent)
    }

    /**
     * 获取权限成功
     *
     * @param requestCode
     */
    fun permissionSuccess(requestCode: Int) {
        Log.d(TAG, "获取权限成功=$requestCode")
    }
}