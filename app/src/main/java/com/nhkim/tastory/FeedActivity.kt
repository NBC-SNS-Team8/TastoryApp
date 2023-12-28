package com.nhkim.tastory

import android.app.Dialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet.Constraint
import androidx.constraintlayout.widget.ConstraintSet.Layout

class FeedActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed)

        val iv_btn_mypage = findViewById<ImageButton>(R.id.iv_btn_mypage)
        val cl_feed = findViewById<ConstraintLayout>(R.id.cl_feed)
        val cl_feed2 = findViewById<ConstraintLayout>(R.id.cl_feed2)

        showDialog()

//    if (intent.hasExtra("id")) {
//        tv_welcome = intent.getStringExtra("id") + "님, 환영합니다!"  // 영정님, id intent 키가 뭔가요??
//    }

        iv_btn_mypage.setOnClickListener{
            val intent = Intent(this, MyPageActivity::class.java)
            startActivity(intent)
        }

        cl_feed.setOnClickListener{
            val intent = Intent(this, DetailActivity::class.java)
            startActivity(intent)
        }

        cl_feed2.setOnClickListener {
            val intent = Intent(this, MyDetailActivity::class.java)
            startActivity(intent)
        }
    }

    private fun showDialog() {
        // AlertDialog 초기화
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)

        // 제목 설정
        builder.setTitle("\uD83C\uDF8A당첨\uD83C\uDF89")
        builder.setMessage("이벤트 쿠폰 당첨!!\n" +
                "이번 달 신상 맛집 할인 쿠폰을 드립니다. 쿠폰함을 확인해 주세요.")

        // 다이얼로그 화면 설정
        val inflater: LayoutInflater = layoutInflater
        builder.setView(inflater.inflate(R.layout.custom_dialog, null))

        // 닫기 이벤트
        builder.setPositiveButton("닫기") {
            Dialog, p1 -> Dialog.cancel()
        }

        val alertDialog: AlertDialog = builder.create()
        alertDialog.show()
    }
}
