package com.irfan.spikapps.ui.homeall.home

import android.os.Bundle
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import com.irfan.spikapps.R

class KamusActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kamus)

        val webView = findViewById<WebView>(R.id.webView)
        webView.loadUrl("http://kamus-sibi.com/pencarian")
    }
}