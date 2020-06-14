package com.fhhy.phoenix.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.fhhy.phoenix.login.fragment.LoginFragment

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportFragmentManager.beginTransaction().apply {
            replace(android.R.id.content, LoginFragment.create(), "LoginFragment")
            commit()
        }
    }

    override fun onBackPressed() {
        val loginFragment = supportFragmentManager.findFragmentByTag("LoginFragment")
        if (loginFragment != null) {
            loginFragment.apply {
                this as LoginFragment
                toPreState()
            }
        }else {
            super.onBackPressed()
        }
    }
}