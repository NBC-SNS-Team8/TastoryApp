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
        val writer = findViewById<TextView>(R.id.tv_writer)
        val writerProfile = findViewById<ImageView>(R.id.iv_writer_profile)
        val nickTitle = findViewById<TextView>(R.id.tv_blog_title)
        val blogContent = findViewById<TextView>(R.id.tv_blog_content)
        val blogImage = findViewById<ImageView>(R.id.iv_blog_image)

        val receivedUser = intent.getParcelableExtra<User>("USER")
        val receivedPost = intent.getParcelableExtra<Post>("POST")
        val rcPostName = receivedPost?.name.toString()
        val rcPostImage = receivedPost?.thumbnail.toString()
        val rcPostTitle = receivedPost?.title.toString()
        val rcPostContent = receivedPost?.longContent.toString()

        blogName.text = rcPostName + "님의 블로그"
        writer.text = rcPostName
        writerProfile.setImageResource(receivedPost!!.profile)
        nickTitle.text = rcPostTitle
        blogContent.text = rcPostContent
        blogImage.setImageResource(receivedPost.thumbnail)
        blogName.text = rcPostName + getString(R.string.name)

        backBtn.setOnClickListener {
            finish()
            overridePendingTransition(R.anim.fadein, R.anim.fadeout)
        }

    }
}