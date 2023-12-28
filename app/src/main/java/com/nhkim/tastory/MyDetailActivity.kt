package com.nhkim.tastory

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class MyDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_detail)


        val btn = findViewById<ImageView>(R.id.imageView)

        btn.setOnClickListener {
            finish()
        }
    }
}