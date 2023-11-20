package com.example.pixselchat_live

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView

/**
 * An Activity representing the initial page of PixselChat Live
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val splashLogo: ImageView = findViewById(R.id.liveSplashLogo)
        val splashText: TextView = findViewById(R.id.liveSplashText)

        val onClickListener = View.OnClickListener { _ ->
            val intent = Intent(this@MainActivity, TranslationActivity::class.java)
            startActivity(intent)
        }

        splashLogo.setOnClickListener(onClickListener)
        splashText.setOnClickListener(onClickListener)
    }
}