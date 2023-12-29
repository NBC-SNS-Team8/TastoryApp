package com.nhkim.tastory

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val backBtn = findViewById<ImageView>(R.id.imageView)
        val blogName = findViewById<TextView>(R.id.tv_blog_name)
        val nickName = findViewById<TextView>(R.id.tv_nick_name)
        val nickTitle = findViewById<TextView>(R.id.tv_blog_title)
        val blogContent = findViewById<TextView>(R.id.tv_blog_content)
        val blogImage = findViewById<ImageView>(R.id.iv_blog_image)

        val receivedUser = intent.getParcelableExtra<User>("USER")
        val receivedPost = intent.getParcelableExtra<Post>("POST")
        val rcPostName = receivedPost?.name.toString()
//        val rcPostImage = receivedPost?.image.toString()
        val rcPostTitle = receivedPost?.title.toString()
        val rcPostContent = receivedPost?.content.toString()

        nickName.text = rcPostName
        nickTitle.text = rcPostTitle
        blogContent.text = rcPostContent
        blogName.text = rcPostName + "님의 블로그"

        backBtn.setOnClickListener {
            finish()
        }

    }
}