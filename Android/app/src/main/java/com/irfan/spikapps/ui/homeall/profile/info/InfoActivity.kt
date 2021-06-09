package com.irfan.spikapps.ui.homeall.profile.info

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.irfan.spikapps.databinding.ActivityInfoBinding
import com.irfan.spikapps.ui.HomeAllActivity

class InfoActivity : AppCompatActivity() {

    private lateinit var infoBinding: ActivityInfoBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        infoBinding = ActivityInfoBinding.inflate(layoutInflater)
        setContentView(infoBinding.root)

        infoBinding.imgback.setOnClickListener {
            val moveIntent = Intent(this, HomeAllActivity::class.java)
            startActivity(moveIntent)
        }

    }
}