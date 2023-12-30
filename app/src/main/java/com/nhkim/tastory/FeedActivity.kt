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
            "크리스마스 추천 맛집!",
            "벌써 2023년 연말이 다가왔습니다~! \n크리스마스와 연말을 맞아 가족 혹은 연인과 함께 방문하기 좋은 맛집을 소개해 드릴게요.\n",
            "벌써 2023년 연말이 다가왔습니다~! \n크리스마스와 연말을 맞아 \n가족 혹은 연인과 함께 방문하기 좋은 맛집을 소개해 드릴게요.\n\n연남동데이트하기 좋은 \n연남동 스테이크 맛집 블루쇼파스타\n\n분위기 있고, 맛도 좋은 연남동스테이크 맛집을 소개해드릴게요 :)\n\n골목이 복잡하지 않고\n번화가쪽에 위치하고 있는 것이 \n아니라 찾기 어렵지 않았어요!\n\n 차를 가지고 오셔도 걱정하실 것이 없는 게 근처에 연남동 공영주차장이 있어서\n거기에 주차를 하고 와서 이용하기에도 나쁘지 않을 것 같았어요."
        )

        val secondPost = Post(
            "덕만",
            R.drawable.profile2,
            R.drawable.makchang, // 이 부분은 이미지 리소스 ID가 아닌 뷰의 ID입니다. 이미지 리소스를 사용하려면 R.drawable.xxx 형식의 ID를 사용해야 합니다.
            "힙지로에 돼지꼬리구이 막창 맛집 '을지로1막'",
            "저번주에 처음으로 을지로 3가역, 힙지로를 가보았다. 친구들에게 얘기만 듣다가 가보니까 신세계였다.",
            "저번주에 처음으로 을지로 3가역, 힙지로를 가보았다. 친구들에게 얘기만 듣다가 가보니까 신세계였다. \n\n그래서 며칠 뒤 친구를 꼬셔서 또 갔다왔다. 친구와 다녀온 곳은 '을지1막'이다. \n\n우리는 일찍 도착해서 대기가 없었지만 먹고 나갈 쯤엔 대기가 있었다. 솔직히 20대 보단 우리같은 나이 좀 있는 고객들이 많았다. 다들 퇴근하고 온 듯"
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

        tv_welcome.text = receivedUser?.name + "님 환영합니다!"

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
