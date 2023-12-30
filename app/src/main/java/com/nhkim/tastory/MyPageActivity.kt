package com.nhkim.tastory

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout

class MyPageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_page)


        val xIcon = findViewById<ImageView>(R.id.x_icon)
        val blogName = findViewById<TextView>(R.id.tv_blog_name)
        val blogId = findViewById<TextView>(R.id.tv_blog_id)
        val receivedUser = intent.getParcelableExtra<User>("USER")
        val myPost = findViewById<ConstraintLayout>(R.id.my_post)
        val postImage = findViewById<ImageView>(R.id.my_post_image)

        val firstPost = Post(
            receivedUser?.name,
            R.drawable.profile1,
            R.drawable.steak,
            getString(R.string.stake_title),
            getString(R.string.stake_review_preview),
            getString(R.string.stake_review_preview) + getString(R.string.steak_review_contents)
        )

        blogName.text = receivedUser?.name
        blogId.text = getString(R.string.my_id) + receivedUser?.id
        postImage.clipToOutline = true


        xIcon.setOnClickListener {
            finish()
            overridePendingTransition(R.anim.slide_down_enter, R.anim.slide_down_exit)
        }

        myPost.setOnClickListener {
            val intent = Intent(this, DetailActivity::class.java).apply {
                putExtra("USER", receivedUser)
                putExtra("POST", firstPost)
            }
            startActivity(intent)
            overridePendingTransition(R.anim.fadein, R.anim.fadeout)
        }
    }

}