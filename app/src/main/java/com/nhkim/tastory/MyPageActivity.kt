package com.nhkim.tastory

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView

class MyPageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_page)

        val xIcon = findViewById<ImageView>(R.id.x_icon)
        val blogName = findViewById<TextView>(R.id.tv_blog_name)
        val blogId = findViewById<TextView>(R.id.tv_blog_id)

        val receivedUser = intent.getParcelableExtra<User>("USER")
        blogName.text = receivedUser?.name
        blogId.text = receivedUser?.id

        xIcon.setOnClickListener {
            finish()
        }
    }


}