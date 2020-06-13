package com.fhhy.phoenix.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.fhhy.phoenix.login.fragment.LoginFragment

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportFragmentManager.beginTransaction().apply {
            replace(android.R.id.content, LoginFragment.create())
            commit()
        }
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 1) {
            supportFragmentManager.popBackStack()
        }else {
            super.onBackPressed()
        }
    }
}