package com.nhkim.tastory

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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

        val firstPost = Post(
            receivedUser?.name,
            R.drawable.profile1,
            R.drawable.steak,
            "크리스마스 추천 맛집!",
            "벌써 2023년 연말이 다가왔습니다~! \n크리스마스와 연말을 맞아 가족 혹은 연인과 함께 방문하기 좋은 맛집을 소개해 드릴게요.\n",
            "벌써 2023년 연말이 다가왔습니다~! \n크리스마스와 연말을 맞아 \n가족 혹은 연인과 함께 방문하기 좋은 맛집을 소개해 드릴게요.\n\n연남동데이트하기 좋은 \n연남동 스테이크 맛집 블루쇼파스타\n\n분위기 있고, 맛도 좋은 연남동스테이크 맛집을 소개해드릴게요 :)\n\n골목이 복잡하지 않고\n번화가쪽에 위치하고 있는 것이 \n아니라 찾기 어렵지 않았어요!\n\n 차를 가지고 오셔도 걱정하실 것이 없는 게 근처에 연남동 공영주차장이 있어서\n거기에 주차를 하고 와서 이용하기에도 나쁘지 않을 것 같았어요."
        )

        blogName.text = receivedUser?.name
        blogId.text = receivedUser?.id

        val myPost = findViewById<ConstraintLayout>(R.id.my_post)


        xIcon.setOnClickListener {
            finish()
        }

        myPost.setOnClickListener {
            val intent = Intent(this, DetailActivity::class.java).apply {
                putExtra("USER", receivedUser)
                putExtra("POST", firstPost)
            }
            startActivity(intent)

        }
    }

}