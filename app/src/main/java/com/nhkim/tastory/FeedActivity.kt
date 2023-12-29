package com.nhkim.tastory

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import org.w3c.dom.Text

class FeedActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed)

        val receivedUser = intent.getParcelableExtra<User>("USER")
        val iv_btn_mypage = findViewById<ImageButton>(R.id.iv_btn_mypage)
        val cl_feed = findViewById<ConstraintLayout>(R.id.cl_feed)
//        val cl_feed2 = findViewById<ConstraintLayout>(R.id.cl_feed2)
        val tv_welcome = findViewById<TextView>(R.id.tv_welcome)
//        val writer = findViewById<TextView>(R.id.tv_chopa)

        val firstPost = Post(
            receivedUser?.name,
            R.drawable.profile1,
            R.drawable.steak,
            "크리스마스 추천 맛집!",
            "벌써 2023년 연말이 다가왔습니다~! \n크리스마스와 연말을 맞아 가족 혹은 연인과 함께 방문하기 좋은 맛집을 소개해 드릴게요.\n"
        )

        val secondPost = Post(
            "덕만",
            R.drawable.profile2,
            R.drawable.makchang, // 이 부분은 이미지 리소스 ID가 아닌 뷰의 ID입니다. 이미지 리소스를 사용하려면 R.drawable.xxx 형식의 ID를 사용해야 합니다.
            "힙지로에 돼지꼬리구이 막창 맛집 '을지로1막'",
            "저번주에 처음으로 을지로 3가역, 힙지로를 가보았다. 친구들에게 얘기만 듣다가 가보니까 신세계였다."
        )
        val feedItem1 = findViewById<ConstraintLayout>(R.id.feed_item1)
        val writer1Name = feedItem1.findViewById<TextView>(R.id.tv_blog_writer)
        val blogThumbnail1 = feedItem1.findViewById<ImageView>(R.id.iv_blog_thumbnail)
        val blogProfile1 = feedItem1.findViewById<ImageView>(R.id.iv_profile)
        val blogTitle1 = feedItem1.findViewById<TextView>(R.id.tv_blog_title)
        val blogContent1 = feedItem1.findViewById<TextView>(R.id.tv_blog_content)

        writer1Name.text = firstPost.name

        blogThumbnail1.setImageResource(firstPost.thumbnail)
        blogProfile1.setImageResource(firstPost.profile)
        blogTitle1.text = firstPost.title
        blogContent1.text = firstPost.content

        val feedItem2 = findViewById<ConstraintLayout>(R.id.feed_item2)
        val writer2Name = feedItem2.findViewById<TextView>(R.id.tv_blog_writer)
        val blogThumbnail2 = feedItem2.findViewById<ImageView>(R.id.iv_blog_thumbnail)
        val blogProfile2 = feedItem2.findViewById<ImageView>(R.id.iv_profile)
        val blogTitle2 = feedItem2.findViewById<TextView>(R.id.tv_blog_title)
        val blogContent2 = feedItem2.findViewById<TextView>(R.id.tv_blog_content)

        writer2Name.text = secondPost.name
        blogThumbnail2.setImageResource(secondPost.thumbnail)
        blogProfile2.setImageResource(secondPost.profile)
        blogTitle2.text = secondPost.title
        blogContent2.text = secondPost.content


        showDialog()

        tv_welcome.text = receivedUser?.name + getString(R.string.welcome)
//        writer.text = receivedUser?.name

        iv_btn_mypage.setOnClickListener{    // 버튼 누르면 마이페이지 화면으로 이동
            val intent = Intent(this, MyPageActivity::class.java).apply {
                putExtra("USER", receivedUser)
            }
            startActivity(intent)
        }

        cl_feed.setOnClickListener{
//            val post = Post(receivedUser?.name, "R.id.cl_feed", "크리스마스 추천 맛집!", "벌써 2023년 연말이 다가왔습니다~! \n크리스마스와 연말을 맞아 \n가족 혹은 연인과 함께 방문하기 좋은 맛집을 소개해 드릴게요.\n\n연남동데이트하기 좋은 \n연남동 스테이크 맛집 블루쇼파스타\n\n분위기 있고, 맛도 좋은 연남동스테이크 맛집을 소개해드릴게요 :)\n\n골목이 복잡하지 않고\n번화가쪽에 위치하고 있는 것이 \n아니라 찾기 어렵지 않았어요!\n\n 차를 가지고 오셔도 걱정하실 것이 없는 게 근처에 연남동 공영주차장이 있어서\n거기에 주차를 하고 와서 이용하기에도 나쁘지 않을 것 같았어요.")
//            val intent = Intent(this, DetailActivity::class.java).apply {
//                putExtra("USER", receivedUser)
//                putExtra("POST", post)
//            }
//            startActivity(intent)
        }

//        cl_feed2.setOnClickListener {
//            val post = Post("덕만", "R.id.cl_feed", "힙지로에 돼지꼬리구이 막창 맛집 '을지로1막'", "저번주에 처음으로 을지로 3가역, 힙지로를 가보았다. 친구들에게 얘기만 듣다가 가보니까 신세계였다. \n\n그래서 며칠 뒤 친구를 꼬셔서 또 갔다왔다. 친구와 다녀온 곳은 '을지1막'이다. \n\n우리는 일찍 도착해서 대기가 없었지만 먹고 나갈 쯤엔 대기가 있었다. 솔직히 20대 보단 우리같은 나이 좀 있는 고객들이 많았다. 다들 퇴근하고 온 듯")
//            val intent = Intent(this, DetailActivity::class.java).apply {
//                putExtra("USER", receivedUser)
//                putExtra("POST", post)
//            }
//            startActivity(intent)
//        }
    }

    private fun showDialog() {
        // AlertDialog 초기화
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)

        // 제목 설정
        builder.setTitle("\uD83C\uDF8A당첨\uD83C\uDF89")
        builder.setMessage(getString(R.string.lucky) +
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
