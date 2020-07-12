package com.fhhy.phoenix.message

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.fhhy.phoenix.R
import com.jaeger.library.StatusBarUtil
const val TYPE_MSG = "TYPE_MSG"
class MsgListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        StatusBarUtil.setLightMode(this)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN or WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)//软键盘不弹出，在setContentView()之前执行
        window.decorView.setBackgroundColor(ContextCompat.getColor(this, R.color.white))

        //通过不同msgType 区分系统消息还是跟单消息
        val msgType = intent.getIntExtra(TYPE_MSG, 0)

        supportFragmentManager.beginTransaction().apply {
            replace(android.R.id.content, MsgListFragment.create(msgType), MsgListFragment.TAG)
            commit()
        }

    }
}