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

class FeedActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed)

        val tv_welcome = findViewById<TextView>(R.id.tv_welcome)
        val iv_btn_mypage = findViewById<ImageButton>(R.id.iv_btn_mypage)
        val iv_steak = findViewById<ImageView>(R.id.iv_steak)
        val iv_makchang = findViewById<ImageView>(R.id.iv_makchang)

        // 없어도 다이얼로그 나오나? val dialog: LinearLayout = findViewById(R.id.dialog)
        showDialog()

//    if (intent.hasExtra("id")) {
//        tv_welcome = intent.getStringExtra("id") + "님, 환영합니다!"  // 영정님, id intent 키가 뭔가요??
//    }

        iv_btn_mypage.setOnClickListener{    // 버튼 누르면 마이페이지 화면으로 이동
            val intent = Intent(this, MyPageActivity::class.java)
            startActivity(intent)    // finish()로 해야하나요?
        }

        iv_steak.setOnClickListener{
            val intent = Intent(this, DetailActivity::class.java)
            startActivity(intent)
        }

        // 디테일 액티비티2로 바꾸기
        iv_makchang.setOnClickListener {
            val intent = Intent(this, DetailActivity::class.java)
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
