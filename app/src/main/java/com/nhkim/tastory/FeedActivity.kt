package com.nhkim.tastory

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import de.hdodenhof.circleimageview.CircleImageView
import org.w3c.dom.Text

class FeedActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed)

        val receivedUser = intent.getParcelableExtra<User>("USER")
        val iv_btn_mypage = findViewById<CircleImageView>(R.id.iv_btn_mypage)
        val blogPost = findViewById<ConstraintLayout>(R.id.cl_feed)
        val tv_welcome = findViewById<TextView>(R.id.tv_welcome)

        val firstPost = Post(
            receivedUser?.name,
            R.drawable.profile1,
            R.drawable.steak,
            getString(R.string.stake_title),
            getString(R.string.stake_review_preview),
            getString(R.string.stake_review_preview) + getString(R.string.steak_review_contents)
        )

        val secondPost = Post(
            getString(R.string.nickname2),
            R.drawable.profile2,
            R.drawable.makchang, // 이 부분은 이미지 리소스 ID가 아닌 뷰의 ID입니다. 이미지 리소스를 사용하려면 R.drawable.xxx 형식의 ID를 사용해야 합니다.
            getString(R.string.makchang_locate),
            getString(R.string.makchang_review_preview),
            getString(R.string.makchang_review_preview) + getString(R.string.makchang_review_contents)
        )
        val feedItem1 = findViewById<ConstraintLayout>(R.id.feed_item1)
        val writer1Name = feedItem1.findViewById<TextView>(R.id.tv_blog_writer)
        val blogThumbnail1 = feedItem1.findViewById<ImageView>(R.id.iv_blog_thumbnail)
        val blogProfile1 = feedItem1.findViewById<ImageView>(R.id.iv_profile)
        val blogTitle1 = feedItem1.findViewById<TextView>(R.id.tv_blog_title)
        val blogContent1 = feedItem1.findViewById<TextView>(R.id.tv_blog_content)

        val feedItem2 = findViewById<ConstraintLayout>(R.id.feed_item2)
        val writer2Name = feedItem2.findViewById<TextView>(R.id.tv_blog_writer)
        val blogThumbnail2 = feedItem2.findViewById<ImageView>(R.id.iv_blog_thumbnail)
        val blogProfile2 = feedItem2.findViewById<ImageView>(R.id.iv_profile)
        val blogTitle2 = feedItem2.findViewById<TextView>(R.id.tv_blog_title)
        val blogContent2 = feedItem2.findViewById<TextView>(R.id.tv_blog_content)

        val historyItem1 = findViewById<ConstraintLayout>(R.id.history_item1)
        val historyItem2 = findViewById<ConstraintLayout>(R.id.history_item2)
        val historyItem3 = findViewById<ConstraintLayout>(R.id.history_item3)
        val historyItem4 = findViewById<ConstraintLayout>(R.id.history_item4)
        val historyItem5 = findViewById<ConstraintLayout>(R.id.history_item5)

        val historyImage1 = historyItem1.findViewById<ImageView>(R.id.iv_history_image)
        val historyImage2 = historyItem2.findViewById<ImageView>(R.id.iv_history_image)
        val historyImage3 = historyItem3.findViewById<ImageView>(R.id.iv_history_image)
        val historyImage4 = historyItem4.findViewById<ImageView>(R.id.iv_history_image)
        val historyImage5 = historyItem5.findViewById<ImageView>(R.id.iv_history_image)

        historyImage1.setImageResource(R.drawable.pizza)
        historyImage2.setImageResource(R.drawable.cake)
        historyImage3.setImageResource(R.drawable.chicken)
        historyImage4.setImageResource(R.drawable.ramen)
        historyImage5.setImageResource(R.drawable.noodle)

        historyImage1.clipToOutline = true
        historyImage2.clipToOutline = true
        historyImage3.clipToOutline = true
        historyImage4.clipToOutline = true
        historyImage5.clipToOutline = true

        writer1Name.text = firstPost.name
        blogThumbnail1.setImageResource(firstPost.thumbnail)
        blogThumbnail1.clipToOutline = true
        blogProfile1.setImageResource(firstPost.profile)
        blogTitle1.text = firstPost.title
        blogContent1.text = firstPost.content

        writer2Name.text = secondPost.name
        blogThumbnail2.setImageResource(secondPost.thumbnail)
        blogThumbnail2.clipToOutline = true
        blogProfile2.setImageResource(secondPost.profile)
        blogTitle2.text = secondPost.title
        blogContent2.text = secondPost.content

        showCustomDialog()

        tv_welcome.text = receivedUser?.name + getString(R.string.welcome)

//        writer.text = receivedUser?.name

        iv_btn_mypage.setOnClickListener{    // 버튼 누르면 마이페이지 화면으로 이동
            val intent = Intent(this, MyPageActivity::class.java).apply {
                putExtra("USER", receivedUser)
            }
            startActivity(intent)
            overridePendingTransition(R.anim.slide_left_enter,R.anim.slide_left_exit)
        }


        // 첫 번째 게시물 클릭 리스너
        feedItem1.setOnClickListener {
            val intent = Intent(this, DetailActivity::class.java).apply {
                putExtra("USER", receivedUser)
                putExtra("POST", firstPost)
            }
            startActivity(intent)
        }

        // 두 번째 게시물 클릭 리스너
        feedItem2.setOnClickListener {
            val intent = Intent(this, DetailActivity::class.java).apply {
                putExtra("USER", receivedUser)
                putExtra("POST", secondPost)
            }
            startActivity(intent)
        }

        historyImage1.setOnClickListener{
            showHistoryDialog()
        }

        historyImage2.setOnClickListener{
            showHistoryDialog()
        }

        historyImage3.setOnClickListener{
            showHistoryDialog()
        }

        historyImage4.setOnClickListener{
            showHistoryDialog()
        }

        historyImage5.setOnClickListener{
            showHistoryDialog()
        }

    }

    fun showCustomDialog() {

        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_custom)
        dialog.setCanceledOnTouchOutside(false)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val closeButton = dialog.findViewById<Button>(R.id.btn_close)
        closeButton.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }

    fun showHistoryDialog() {

        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_history)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        dialog.show()
    }


}
