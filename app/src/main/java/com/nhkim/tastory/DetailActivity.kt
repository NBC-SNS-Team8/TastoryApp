package com.nhkim.tastory

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        var btn =findViewById<ImageView>(R.id.imageView)


        btn.setOnClickListener {
            //finish()
            val intent = Intent(this, FeedActivity::class.java)
            startActivity(intent)
        }

        val strData = intent.getStringArrayExtra("clickedPicture")
        val editPicture = findViewById<ImageView>(R.id.imageView3)





    }
}