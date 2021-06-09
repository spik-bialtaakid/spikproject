package com.irfan.spikapps.ui.homeall.profile.kp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.irfan.spikapps.databinding.ActivityKPBinding
import com.irfan.spikapps.ui.HomeAllActivity

class KPActivity : AppCompatActivity() {

    private lateinit var kpBinding: ActivityKPBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        kpBinding = ActivityKPBinding.inflate(layoutInflater)
        setContentView(kpBinding.root)

        kpBinding.imgback.setOnClickListener {
            val moveIntent = Intent(this, HomeAllActivity::class.java)
            startActivity(moveIntent)
        }
    }
}