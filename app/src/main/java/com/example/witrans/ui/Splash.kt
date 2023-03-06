package com.example.witrans.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.witrans.MainActivity
import com.example.witrans.R
import com.example.witrans.preference.AuthPreference

class Splash : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val isLogin=AuthPreference(this).auth.isLogin
        if(isLogin){
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }else{
            startActivity(Intent(this,LoginActivity::class.java))
            finish()
        }

    }

    override fun onBackPressed() {

    }
}