package com.fhhy.phoenix.contractdetail

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.fhhy.phoenix.R

class ContractDetailActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN or WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)//软键盘不弹出，在setContentView()之前执行
        window.decorView.setBackgroundColor(resources.getColor(R.color.global_bg))

        supportFragmentManager.beginTransaction().apply {
            replace(android.R.id.content, ContractDetailFragment.create(), "LoginFragment")
            commit()
        }

    }
}