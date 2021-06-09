package com.irfan.spikapps.ui.homelogin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.irfan.spikapps.R
import com.irfan.spikapps.databinding.ActivityHomeLoginBinding
import com.irfan.spikapps.ui.homelogin.auth.signin.SigninFragment
import com.irfan.spikapps.ui.homelogin.auth.signup.SignUpFragment


class HomeLoginActivity : AppCompatActivity(){


    private lateinit var homeLoginBinding: ActivityHomeLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeLoginBinding = ActivityHomeLoginBinding.inflate(layoutInflater)
        setContentView(homeLoginBinding.root)


        homeLoginBinding.btnSignIn.setOnClickListener{
            val fragment: Fragment = SigninFragment()
            val fragmentManager: FragmentManager = supportFragmentManager
            fragmentManager.beginTransaction().replace(R.id.homeLogin, fragment).commit()
        }

        homeLoginBinding.btnSignUp.setOnClickListener {
            val fm: Fragment = SignUpFragment()
            val fragmentManager: FragmentManager = supportFragmentManager
            fragmentManager.beginTransaction().replace(R.id.homeLogin, fm).commit()
        }

    }


}