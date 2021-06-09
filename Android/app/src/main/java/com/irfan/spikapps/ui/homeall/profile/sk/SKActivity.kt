package com.irfan.spikapps.ui.homeall.profile.sk

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.irfan.spikapps.R
import com.irfan.spikapps.databinding.ActivityInfoBinding
import com.irfan.spikapps.databinding.ActivitySKBinding
import com.irfan.spikapps.ui.HomeAllActivity
import com.irfan.spikapps.ui.homeall.profile.ProfileFragment

class SKActivity : AppCompatActivity() {

    private lateinit var skBinding: ActivitySKBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        skBinding = ActivitySKBinding.inflate(layoutInflater)
        setContentView(skBinding.root)

        skBinding.imgback.setOnClickListener {
            val moveIntent = Intent(this, HomeAllActivity::class.java)
            startActivity(moveIntent)
        }
    }
}