package com.irfan.spikapps.ui.splashscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.google.firebase.auth.FirebaseAuth
import com.irfan.spikapps.databinding.ActivitySplashScreenBinding
import com.irfan.spikapps.ui.HomeAllActivity
import com.irfan.spikapps.ui.homelogin.HomeLoginActivity

class SplashScreenActivity : AppCompatActivity() {

    private lateinit var splashScreenBinding: ActivitySplashScreenBinding
    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        splashScreenBinding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(splashScreenBinding.root)

        mAuth = FirebaseAuth.getInstance()
        val user = mAuth.currentUser

        Handler(mainLooper).postDelayed({
            if (user != null) {
                val homeSiginAllIntent = Intent(this, HomeAllActivity::class.java)
                startActivity(homeSiginAllIntent)
                finishAffinity()
            }else{
                val homeLoginIntent = Intent(this, HomeLoginActivity::class.java)
                startActivity(homeLoginIntent)
                finishAffinity()
            }
            },3000)


    }
}