package com.nhkim.tastory

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener

class SignUpActivity : AppCompatActivity() {

    //모든 editText와 textView를 변수로 받아오기
    //by lazy로 지연초기화해서 메모리를 효율적으로 사용할 수 있다
    private val editTextSignupId: EditText by lazy {
        findViewById(R.id.et_signup_id)
    }
    private val editTextSignupPw: EditText by lazy {
        findViewById(R.id.et_signup_pw)
    }
    private val editTextSignupName: EditText by lazy {
        findViewById(R.id.et_signup_name)
    }
    private val editTextSignupBirthYear: EditText by lazy {
        findViewById(R.id.et_signup_birth_year)
    }
    private val editTextSignupBirthMonth: EditText by lazy {
        findViewById(R.id.et_signup_birth_month)
    }
    private val editTextSignupBirthDay: EditText by lazy {
        findViewById(R.id.et_signup_birth_day)
    }


    private val tvIdCheck: TextView by lazy {
        findViewById(R.id.tv_signup_id_check)
    }
    private val tvPwCheck: TextView by lazy {
        findViewById(R.id.tv_signup_pw_check)
    }
    private val tvNameCheck: TextView by lazy {
        findViewById(R.id.tv_signup_name_check)
    }
    private val tvBirthCheck: TextView by lazy {
        findViewById(R.id.tv_signup_birth_check)
    }


    //값을 입력받을 editText들을 list로 묶어둠
    private val etList
        get() = listOf(
            editTextSignupId,
            editTextSignupPw,
            editTextSignupName,
            editTextSignupBirthYear,
            editTextSignupBirthMonth,
            editTextSignupBirthDay
        )


    //정규표현식 Regular Expression
    //pattern 범위 내에서 하나라도 포함하는 경우에 대한 코드
    private val regEn = Regex("[A-Z]")
    private val regNum = Regex("[0-9]")
    private val regSpecial = Regex("[~!@#\$%^&*_\\-+=`|:;,.?/]")

    //ID 중복 체크를 하기 위해 이미 등록되어 있는 가상의 아이디 리스트를 만들어 둠
    private val alreadyExistIds =
        listOf("chopa12", "dm_29", "Id23", "nhKim", "ga_hyun", "you_jm", "cyjTired")


    //각 editText에 입력된 값의 유효성을 체크하는 변수
    private var validId = false
    private var validPw = false
    private var validName = false
    private var validBirthYear = false
    private var validBirthMonth = false
    private var validBirthDay = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val btnReturn = findViewById<ConstraintLayout>(R.id.cl_signup_return)
        val btnSignup = findViewById<ConstraintLayout>(R.id.cl_signup_btn)

        initView()

        //뒤로가기 버튼 눌렀을 때 그냥 finish로 화면 닫기
        btnReturn.setOnClickListener {
            finish()
            overridePendingTransition(R.anim.fadein, R.anim.fadeout)
        }

        //회원가입 버튼 눌렀을 떄
        btnSignup.setOnClickListener {

            //입력된 값 중 launcher로 Login에 다시 전달할 값들 변수에 저장
            val userId = editTextSignupId.text.toString()
            val userPw = editTextSignupPw.text.toString()
            val userName = editTextSignupName.text.toString()

            //모든 입력값이 현재 유효한 값일 떄
            if (validId && validPw && validName && validBirthYear && validBirthMonth && validBirthDay) {

                //Login의 activityResultLauncher에 intent로 값을 전달하고 finish
                val intent = Intent(this, LoginActivity::class.java).apply {
                    putExtra("Id", userId)
                    putExtra("Pw", userPw)
                    putExtra("Name", userName)
                }
                setResult(RESULT_OK, intent)
                if (!isFinishing) finish()
                overridePendingTransition(R.anim.fadein, R.anim.fadeout)

            } else {    //입력값 중 유효하지 않은 값이 있을 때 화면 넘어가지 않고 토스트 메세지 생성
                Toast.makeText(this, getString(R.string.signup_failed), Toast.LENGTH_SHORT).show()
            }

        }


    }


    //화면 시작할 때 view들 초기화하는 함수
    private fun initView() {

        setTextChangedListener()

        setOnFocusChangedListener()

    }

    //각각의 editText에서 매 입력마다 변하는 값을 모니터링하는 함수
    private fun setTextChangedListener() {
        etList.forEach { editText ->
            editText.addTextChangedListener {
                editText.setErrorMessage()
            }
        }
    }


    //각각의 editText에서 focus, 커서가 있는지 없는지 확인해서 커서가 없을 때의 입력완료된 값에 대한 처리를 하는 함수
    private fun setOnFocusChangedListener() {
        etList.forEach { editText ->
            editText.setOnFocusChangeListener { _, hasFocus ->
                if (hasFocus.not()) {
                    editText.setErrorMessage()
                }
            }
        }
    }


    //커서가 있든 입력 진행중이든 어쨌든 그 칸에 있는 입력값에 대한 유효성 검사 기준은 같으니까
    //각 editText마다 현재의 입력값에 대응하는 유효성 검사 메세지를 지정하는 함수
    private fun EditText.setErrorMessage() {
        when (this) {

            //ID 입력에 대한 유효성 검사
            editTextSignupId -> {

                //일단 매번 에러메세지 텍스트 색은 빨간색, 메세지 보이게, 유효값 false로 초기화
                tvIdCheck.setTextColor(Color.parseColor("#F46767"))
                tvIdCheck.isVisible = true
                validId = false

                val inputId = editTextSignupId.text.toString()

                //ID는 비어있을 경우, 이미 있는 ID리스트의 ID와 겹치는 경우, 유효한 경우 총 세 가지
                if (inputId.trim().isEmpty()) {
                    tvIdCheck.text = getString(R.string.error_empty)
                } else if (inputId in alreadyExistIds) {
                    tvIdCheck.text = getString(R.string.check_exist_id)
                } else {
                    tvIdCheck.setTextColor(Color.parseColor("#0069FF"))
                    tvIdCheck.text = getString(R.string.check_valid_Id)
                    validId = true
                }
            }

            //PW 입력에 대한 유효성 검사
            editTextSignupPw -> {

                tvPwCheck.setTextColor(Color.parseColor("#F46767"))
                tvPwCheck.isVisible = true
                validPw = false

                val inputPw = editTextSignupPw.text.toString()

                //PW는 비어있을 경우, 영문대문자가 없거나 숫자가 없는 경우, 특수문자가 없는 경우, 길이가 8자 이상이 아닐 경우, 모두 통과해서 유효한 경우
                when {
                    inputPw.isBlank() -> {
                        tvPwCheck.text = getString(R.string.error_empty)
                    }

                    regEn.containsMatchIn(inputPw).not() -> {
                        tvPwCheck.text = getString(R.string.check_invalid_pw_char)
                    }

                    regNum.containsMatchIn(inputPw).not() -> {
                        tvPwCheck.text = getString(R.string.check_invalid_pw_char)
                    }

                    regSpecial.containsMatchIn(inputPw).not() -> {
                        tvPwCheck.text = getString(R.string.check_invalid_pw_special)
                    }

                    inputPw.length !in 8..20 -> {
                        tvPwCheck.text = getString(R.string.check_invalid_pw_length)
                    }

                    else -> {
                        tvPwCheck.setTextColor(Color.parseColor("#0069FF"))
                        tvPwCheck.text = getString(R.string.check_valid_pw)
                        validPw = true
                    }
                }
            }

            //이륾 입력에 대한 유효성 검사
            editTextSignupName -> {

                tvNameCheck.setTextColor(Color.parseColor("#F46767"))
                tvNameCheck.isVisible = true
                validName = false

                val inputName = editTextSignupName.text.toString()

                //이름은 비어있거나, 특수문자가 들어가거나, 유효한 경우
                if (inputName.isBlank()) {
                    tvNameCheck.text = getString(R.string.error_empty)
                } else if (regSpecial.containsMatchIn(inputName)) {
                    tvNameCheck.text = getString(R.string.check_invalid_name)
                } else {
                    tvNameCheck.text = ""
                    validName = true
                }
            }

            //생년 입력에 대한 유효성 검사
            editTextSignupBirthYear -> {

                //생년월일의 경우 유효성 에러메세지는 한 줄인데 검사할 값은 년, 월, 일 세 개니까
                //세 값 중 하나라도 유효하지 않으면 무조건 에러메세지가 보이는 상태가 되도록 조건문 추가
                if (!validBirthYear || !validBirthMonth || !validBirthDay) {
                    tvBirthCheck.setTextColor(Color.parseColor("#F46767"))
                    tvBirthCheck.isVisible = true
                }
                validBirthYear = false

                val inputBirthYear = editTextSignupBirthYear.text.toString()

                //출생년도는 비어있거나, 4자리 숫자로 1900~2023까지의 범위에 포함되지 않거나, 유효한 경우
                if (inputBirthYear.isBlank()) {
                    tvBirthCheck.text = getString(R.string.error_empty)
                } else if (inputBirthYear.toInt() !in 1900..2023) {
                    tvBirthCheck.text = getString(R.string.check_birth_year)
                } else {
                    tvBirthCheck.text = ""
                    validBirthYear = true
                }
            }

            //태어난 월 입력에 대한 유효성 검사
            editTextSignupBirthMonth -> {

                if (!validBirthYear || !validBirthMonth || !validBirthDay) {
                    tvBirthCheck.setTextColor(Color.parseColor("#F46767"))
                    tvBirthCheck.isVisible = true
                }
                validBirthMonth = false

                val inputBirthMonth = editTextSignupBirthMonth.text.toString()

                //출생 월은 비어있거나, 1~12범위를 벗어났거나, 유효한 경우
                if (inputBirthMonth.isBlank()) {
                    tvBirthCheck.text = getString(R.string.error_empty)
                } else if (inputBirthMonth.toInt() !in 1..12) {
                    tvBirthCheck.text = getString(R.string.check_birth_month)
                } else {
                    tvBirthCheck.text = ""
                    validBirthMonth = true
                }

            }

            //태어난 날짜 입력에 대한 유효성 검사
            editTextSignupBirthDay -> {

                if (!validBirthYear || !validBirthMonth || !validBirthDay) {
                    tvBirthCheck.setTextColor(Color.parseColor("#F46767"))
                    tvBirthCheck.isVisible = true
                }
                validBirthDay = false

                val inputBirthDay = editTextSignupBirthDay.text.toString()

                //출생일은 비어있거나, 1~31범위 밖이거나, 유효하거나
                if (inputBirthDay.isBlank()) {
                    tvBirthCheck.text = getString(R.string.error_empty)
                } else if (inputBirthDay.toInt() !in 1..31) {
                    tvBirthCheck.text = getString(R.string.check_birth_day)
                } else {
                    tvBirthCheck.text = ""
                    validBirthDay = true
                }
            }

            //else에 딱히 들어갈 내용이 없으면 Unit을 쓰면 된다
            else -> Unit

        }
    }


}