package com.nhkim.tastory

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener

//전역변수로 activity Result Launcher 지연초기화 선언
lateinit var activityResultLauncher: ActivityResultLauncher<Intent>

class LoginActivity : AppCompatActivity() {

    private val editTextLoginId: EditText by lazy {
        findViewById(R.id.et_login_id)
    }
    private val editTextLoginPw: EditText by lazy {
        findViewById(R.id.et_login_pw)
    }
    private val btnLogin: ConstraintLayout by lazy {
        findViewById(R.id.cl_login_btn)
    }
    private val btnSignupPage: ConstraintLayout by lazy {
        findViewById(R.id.cl_login_signup_btn)
    }

    //입력값을 받는 editText들을 한꺼번에 묶어서 전달하는 용도
    private val etList
        get() = listOf(
            editTextLoginId,
            editTextLoginPw
        )

    //Extra로 전달할 Id, 이름 값 초기화
    private var userId: String = ""
    private var userName: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initView()


        //로그인 버튼이 눌렸을 때
        btnLogin.setOnClickListener {
            //아이디 editText에 입력된 값이나 비밀번호 editText에 입력된 값 중 하나라도 비어있을 경우
            if (editTextLoginId.text.trim().isEmpty() || editTextLoginPw.text.trim().isEmpty()) {
                Toast.makeText(this, getString(R.string.error_empty), Toast.LENGTH_SHORT).show()
            } else {    //아이디 비밀번호 모두 공백이 아닌 값이 있을 경우에는 User클래스에 Id, Name값을 입력하고 그 User객체를 Extra로 전달, Feed Activity 시작
                val user = User(userId, userName)
                val intent = Intent(this, FeedActivity::class.java).apply {
                    putExtra("USER", user)
                }
                startActivity(intent)
                overridePendingTransition(R.anim.fadein, R.anim.fadeout)

            }

        }

        //회원가입 버튼이 눌렸을 때 값을 다시 돌려받기 위해 launcher로 Signup Activity 호출
        btnSignupPage.setOnClickListener {
            var intent = Intent(this, SignUpActivity::class.java)
            activityResultLauncher.launch(intent)
            overridePendingTransition(R.anim.fadein, R.anim.fadeout)
        }


    }

    //해당 액티비티가 OnCreate되었을 때 view들을 초기화하는 함수
    private fun initView() {

        //지연초기화로 선언한 activityResultLauncher의 값 선언 - launch로 호출했던 액티비티에서 다시 넘어온 값 받아서 저장
        activityResultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                //Signup에서 값을 넘겨주면서 지정한 resultCode 값 확인하고 맞으면 값 받아서 저장하고 입력
                if (it.resultCode == RESULT_OK) {
                    userId = it.data?.getStringExtra("Id") ?: ""
                    val userPw = it.data?.getStringExtra("Pw") ?: ""
                    userName = it.data?.getStringExtra("Name") ?: ""

                    editTextLoginId.setText(userId)
                    editTextLoginPw.setText(userPw)
                }
            }

        setTextChangedListener()

    }

    //id,pw 입력 칸이 공백이 아닌지 실시간으로 알 수 있는 함수
    //editText들을 리스트로 한꺼번에 넣어서 forEach로 각각에 함수 적용
    //editText의 매 입력마다 변화를 감지(?)
    private fun setTextChangedListener() {
        etList.forEach { editText ->
            editText.addTextChangedListener {
                //공백일 경우 밑줄 색과 hint message 바뀜
                if (editText.text.toString().isBlank()) {
                    editText.backgroundTintList =
                        ContextCompat.getColorStateList(applicationContext, R.color.tp_red)
                    editText.hint = getString(R.string.error_empty)
                } else {    //공백이 아닐 경우 다시 밑줄 보이지 않게 돌아감
                    editText.backgroundTintList =
                        ContextCompat.getColorStateList(applicationContext, R.color.transparent)
                }
            }
        }
    }


}