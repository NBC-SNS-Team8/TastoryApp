package com.nhkim.tastory

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener

class SignUpActivity : AppCompatActivity() {

//    private val btnReturn: ConstraintLayout by lazy {
//        findViewById(R.id.cl_signup_return)
//    }
//    private val editTextSignupId: EditText by lazy {
//        findViewById(R.id.et_signup_id)
//    }
//    private val editTextSignupPw: EditText by lazy {
//        findViewById(R.id.et_signup_pw)
//    }
//    private val editTextSignupName: EditText by lazy {
//        findViewById(R.id.et_signup_name)
//    }
//    private val editTextSignupBirthYear: EditText by lazy {
//        findViewById(R.id.et_signup_birth_year)
//    }
//    private val editTextSignupBirthMonth: EditText by lazy {
//        findViewById(R.id.et_signup_birth_month)
//    }
//    private val editTextSignupBirthDay: EditText by lazy {
//        findViewById(R.id.et_signup_birth_day)
//    }
//    private val btnSignup: ConstraintLayout by lazy {
//        findViewById(R.id.cl_signup_btn)
//    }
//
//    private val tvIdCheck: TextView by lazy {
//        findViewById(R.id.tv_signup_id_check)
//    }
//    private val tvPwCheck: TextView by lazy {
//        findViewById(R.id.tv_signup_pw_check)
//    }


    //    private val etList = listOf(
//        editTextSignupId,
//        editTextSignupPw,
//        editTextSignupName,
//        editTextSignupBirthYear,
//        editTextSignupBirthMonth,
//        editTextSignupBirthDay
//    )
//

    private val regHan = Regex("[ㄱ-하ㅏ-ㅣ]")
    private val regEn = Regex("[A-Z]")
    private val regNum = Regex("[0-9]")
    private val regSpecial = Regex("[~!@#\$%^&*_\\-+=`|:;,.?/]")

    private val alreadyExistIds =
        listOf("chopa12", "dm_29", "Id23", "nhKim", "ga_hyun", "you_jm", "cyjTired")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)


        val btnReturn = findViewById<ConstraintLayout>(R.id.cl_signup_return)


        val editTextSignupId = findViewById<EditText>(R.id.et_signup_id)

        val editTextSignupPw = findViewById<EditText>(R.id.et_signup_pw)

        val editTextSignupName = findViewById<EditText>(R.id.et_signup_name)

        val editTextSignupBirthYear = findViewById<EditText>(R.id.et_signup_birth_year)

        val editTextSignupBirthMonth = findViewById<EditText>(R.id.et_signup_birth_month)

        val editTextSignupBirthDay = findViewById<EditText>(R.id.et_signup_birth_day)

        val btnSignup = findViewById<ConstraintLayout>(R.id.cl_signup_btn)


        val tvIdCheck = findViewById<TextView>(R.id.tv_signup_id_check)

        val tvPwCheck = findViewById<TextView>(R.id.tv_signup_pw_check)

        val tvNameCheck = findViewById<TextView>(R.id.tv_signup_name_check)

        val tvBirthCheck = findViewById<TextView>(R.id.tv_signup_birth_check)


        val etList = listOf(
            editTextSignupId,
            editTextSignupPw,
            editTextSignupName,
            editTextSignupBirthYear,
            editTextSignupBirthMonth,
            editTextSignupBirthDay
        )


        editTextSignupId.addTextChangedListener {
            tvIdCheck.setTextColor(Color.RED)
            val inputId = editTextSignupId.text.toString()
            if (inputId.trim().isEmpty()) {
                tvIdCheck.text = getString(R.string.error_empty)

            } else if (editTextSignupId.text.toString() in alreadyExistIds) {
                tvIdCheck.text = getString(R.string.check_exist_id)
            } else {
                tvIdCheck.text = ""
            }
        }

        editTextSignupPw.addTextChangedListener {
            tvPwCheck.setTextColor(Color.RED)
            val inputPw = editTextSignupPw.text.toString()
            when {
                inputPw.isBlank() -> {
                    tvPwCheck.text = getString(R.string.error_empty)
                }

                inputPw.matches(regHan) -> {
                    tvPwCheck.text = getString(R.string.check_invalid_pw_char_han)
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
                    tvPwCheck.text = ""
                }
            }
        }

        editTextSignupName.addTextChangedListener {
            tvNameCheck.setTextColor(Color.RED)
            val inputName = editTextSignupName.text.toString()
            if (inputName.isEmpty()) {
                tvNameCheck.text = getString(R.string.error_empty)
            } else if (regSpecial.containsMatchIn(inputName)) {
                tvNameCheck.text = getString(R.string.check_invalid_name)
            } else {
                tvNameCheck.text = ""
            }
        }

        editTextSignupBirthYear.addTextChangedListener {
            val inputBirthYear = editTextSignupBirthYear.text.toString()
            if (inputBirthYear.isEmpty()) {
                tvBirthCheck.text = getString(R.string.error_empty)
            } else {
                if (inputBirthYear.toInt() !in 1900..2023) {
                    tvBirthCheck.text = getString(R.string.check_birth_year)
                } else {
                    tvBirthCheck.text = ""
                }
            }
        }
        editTextSignupBirthMonth.addTextChangedListener {
            val inputBirthMonth = editTextSignupBirthMonth.text.toString()
            if (inputBirthMonth.isEmpty()) {
                tvBirthCheck.text = getString(R.string.error_empty)
            } else {
                if (inputBirthMonth.toInt() !in 1..12) {
                    tvBirthCheck.text = getString(R.string.check_birth_month)
                } else {
                    tvBirthCheck.text = ""
                }
            }
        }
        editTextSignupBirthDay.addTextChangedListener {
            val inputBirthDay = editTextSignupBirthDay.text.toString()
            if (inputBirthDay.isEmpty()) {
                tvBirthCheck.text = getString(R.string.error_empty)
            } else {
                if (inputBirthDay.toInt() !in 1..31) {
                    tvBirthCheck.text = getString(R.string.check_birth_day)
                } else {
                    tvBirthCheck.text = ""
                }
            }
        }




        editTextSignupId.setOnFocusChangeListener { v, hasFocus ->
            tvIdCheck.isVisible = true
            if (hasFocus.not()) {
                if (editTextSignupId.text.toString().isBlank()) {
                    tvIdCheck.text = getString(R.string.error_empty)
                } else {
                    tvIdCheck.setTextColor(Color.GREEN)
                    tvIdCheck.text = getString(R.string.check_valid_Id)
                }
            }
        }

        editTextSignupPw.setOnFocusChangeListener { v, hasFocus ->
            tvPwCheck.isVisible = true
            if (hasFocus.not()) {
                if (editTextSignupPw.text.toString().isBlank()) {
                    tvPwCheck.text = getString(R.string.error_empty)
                } else {
                    val inputPw = editTextSignupPw.text.toString()
                    when {
                        inputPw.matches(regHan) -> {
                            tvPwCheck.text = getString(R.string.check_invalid_pw_char_han)
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
                            tvPwCheck.setTextColor(Color.GREEN)
                            tvPwCheck.text = getString(R.string.check_valid_pw)
                        }
                    }
                }
            }
        }

        editTextSignupName.setOnFocusChangeListener { v, hasFocus ->
            tvNameCheck.isVisible = true
            if (hasFocus.not()) {
                if (editTextSignupName.text.toString().isBlank()) {
                    tvNameCheck.text = getString(R.string.error_empty)
                } else if (regSpecial.containsMatchIn(editTextSignupName.text.toString())) {
                    tvNameCheck.text = getString(R.string.check_invalid_name)
                } else {
                    tvNameCheck.text = ""
                }
            }
        }

        editTextSignupBirthYear.setOnFocusChangeListener { v, hasFocus ->
            tvBirthCheck.isVisible = true
            if (hasFocus.not()) {
                if (editTextSignupBirthYear.text.toString().isBlank()) {
                    tvBirthCheck.text = getString(R.string.error_empty)
                } else if (editTextSignupBirthYear.text.toString().toInt() !in 1900..2023) {
                    tvBirthCheck.text = getString(R.string.check_birth_year)
                } else {
                    tvBirthCheck.text = ""
                }
            }
        }
        editTextSignupBirthMonth.setOnFocusChangeListener { v, hasFocus ->
            tvBirthCheck.isVisible = true
            if (hasFocus.not()) {
                if (editTextSignupBirthMonth.text.toString().isBlank()) {
                    tvBirthCheck.text = getString(R.string.error_empty)
                } else if (editTextSignupBirthMonth.text.toString().toInt() !in 1..12) {
                    tvBirthCheck.text = getString(R.string.check_birth_month)
                } else {
                    tvPwCheck.text = ""
                }
            }
        }
        editTextSignupBirthDay.setOnFocusChangeListener { v, hasFocus ->
            tvBirthCheck.isVisible = true
            if (hasFocus.not()) {
                if (editTextSignupBirthDay.text.toString().isBlank()) {
                    tvBirthCheck.text = getString(R.string.error_empty)
                } else if (editTextSignupBirthDay.text.toString().toInt() !in 1..31) {
                    tvBirthCheck.text = getString(R.string.check_birth_day)
                } else {
                    tvBirthCheck.text = ""
                }
            }
        }


        //하나라도 invalid일 때 버튼 안 눌리게 하는 ...변수?를 만들어야 하나
        //함수 리팩토링은 또 언제 하지...


//        btnSignup.isEnabled = false

//        initView()

        btnReturn.setOnClickListener {
            finish()
        }

        btnSignup.setOnClickListener {
            val userId = editTextSignupId.text.toString()
            val userPw = editTextSignupPw.text.toString()
            val userName = editTextSignupName.text.toString()
//            val userBirth = editTextSignupBirthYear.text.toString() + editTextSignupBirthMonth.text.toString() + editTextSignupBirthDay.text.toString()


            val intent = Intent(this, LoginActivity::class.java).apply {
                putExtra("Id", userId)
                putExtra("Pw", userPw)
                putExtra("Name", userName)
//                putExtra("Birth", userBirth)
            }
            setResult(RESULT_OK, intent)
            if (!isFinishing) finish()
        }


    }


    private fun initView() {

//        editTextSignupId.addTextChangedListener {
//            if (editTextSignupId.text.toString().trim().isEmpty()) {
//                tvIdCheck.text = getString(R.string.error_empty)
//
//            } else if (editTextSignupId.text.toString() in alreadyExistIds) {
//                tvIdCheck.text = getString(R.string.check_exist_id)
//            } else {
//                tvIdCheck.text = ""
//            }
//        }
//
//        editTextSignupPw.addTextChangedListener {
//            val inputPw = editTextSignupPw.text.toString()
//            when {
//                inputPw.isBlank() -> {
//                    tvPwCheck.text = getString(R.string.error_empty)
//                }
//
//                inputPw.contains(regex = Regex("[ㄱ-ㅎ]")) -> {
//                    tvPwCheck.text = getString(R.string.check_invalid_pw_char)
//                }
//
//                inputPw.contains(regex = Regex("[ㅏ-ㅣ]")) -> {
//                    tvPwCheck.text = getString(R.string.check_invalid_pw_char)
//                }
//
//                inputPw.contains(regex = Regex("[^!@#$%^&*()~]")) -> {
//                    tvPwCheck.text = getString(R.string.check_invalid_pw_char)
//                }
//
//                inputPw.contains(regex = Regex("[^A-Z]")) -> {
//                    tvPwCheck.text = getString(R.string.check_invalid_pw_char)
//                }
//
//                inputPw.contains(regex = Regex("[^0-9]")) -> {
//                    tvPwCheck.text = getString(R.string.check_invalid_pw_char)
//                }
//
//                inputPw.length !in 8..20 -> {
//                    tvPwCheck.text = getString(R.string.check_invalid_pw_length)
//                }
//
//                else -> {
//                    tvPwCheck.setTextColor(Color.GREEN)
//                    tvPwCheck.text = getString(R.string.check_valid_pw)
//                }
//            }
//        }


//        editTextSignupId.setOnFocusChangeListener { v, hasFocus ->
//            if (hasFocus.not()) {
//                tvIdCheck.text = getString(R.string.error_empty)
//            }
//        }
//        editTextSignupPw.setOnFocusChangeListener { v, hasFocus ->
//            if (hasFocus.not()) {
//                tvPwCheck.text = getString(R.string.error_empty)
//            }
//        }
//        editTextSignupName.setOnFocusChangeListener { v, hasFocus ->
//            if (hasFocus.not()) {
//                //focus 없을 때
//            }
//        }
//        editTextSignupBirthYear.setOnFocusChangeListener { v, hasFocus ->
//            if (hasFocus.not()) {
//                //focus 없을 때
//            }
//        }
//        editTextSignupBirthMonth.setOnFocusChangeListener { v, hasFocus ->
//            if (hasFocus.not()) {
//                //focus 없을 때
//            }
//        }
//        editTextSignupBirthDay.setOnFocusChangeListener { v, hasFocus ->
//            if (hasFocus.not()) {
//                //focus 없을 때
//            }
//        }

    }


//겹치는 거 함수를 만들어야 하는데... 으아아악

//    private fun signupBtnEnabled() {
//            btnSignup.isEnabled =
//    }

}