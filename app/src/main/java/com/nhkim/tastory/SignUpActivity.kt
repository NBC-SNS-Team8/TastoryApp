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


    private val etList
        get() = listOf(
            editTextSignupId,
            editTextSignupPw,
            editTextSignupName,
            editTextSignupBirthYear,
            editTextSignupBirthMonth,
            editTextSignupBirthDay
        )


    private val regHan = Regex("[ㄱ-하ㅏ-ㅣ]")
    private val regEn = Regex("[A-Z]")
    private val regNum = Regex("[0-9]")
    private val regSpecial = Regex("[~!@#\$%^&*_\\-+=`|:;,.?/]")

    private val alreadyExistIds =
        listOf("chopa12", "dm_29", "Id23", "nhKim", "ga_hyun", "you_jm", "cyjTired")


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

        btnReturn.setOnClickListener {
            finish()
            overridePendingTransition(R.anim.fadein, R.anim.fadeout)
        }

        btnSignup.setOnClickListener {

            val userId = editTextSignupId.text.toString()
            val userPw = editTextSignupPw.text.toString()
            val userName = editTextSignupName.text.toString()

            if (validId && validPw && validName && validBirthYear && validBirthMonth && validBirthDay) {

                val intent = Intent(this, LoginActivity::class.java).apply {
                    putExtra("Id", userId)
                    putExtra("Pw", userPw)
                    putExtra("Name", userName)
                }
                setResult(RESULT_OK, intent)
                if (!isFinishing) finish()
                overridePendingTransition(R.anim.fadein, R.anim.fadeout)

            } else {
                Toast.makeText(this, getString(R.string.signup_failed), Toast.LENGTH_SHORT).show()
            }

        }


    }


    private fun initView() {

        setTextChangedListener()

        setOnFocusChangedListener()

    }

    private fun setTextChangedListener() {
        etList.forEach { editText ->
            editText.addTextChangedListener {
                editText.setErrorMessage()
            }
        }
    }


    private fun setOnFocusChangedListener() {
        etList.forEach { editText ->
            editText.setOnFocusChangeListener { _, hasFocus ->
                if (hasFocus.not()) {
                    editText.setErrorMessage()
                }
            }
        }
    }


    private fun EditText.setErrorMessage() {
        when (this) {

            editTextSignupId -> {

                tvIdCheck.setTextColor(Color.parseColor("#F46767"))
                tvIdCheck.isVisible = true
                validId = false

                val inputId = editTextSignupId.text.toString()

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

            editTextSignupPw -> {

                tvPwCheck.setTextColor(Color.parseColor("#F46767"))
                tvPwCheck.isVisible = true
                validPw = false

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
                        tvPwCheck.setTextColor(Color.parseColor("#0069FF"))
                        tvPwCheck.text = getString(R.string.check_valid_pw)
                        validPw = true
                    }
                }
            }

            editTextSignupName -> {

                tvNameCheck.setTextColor(Color.parseColor("#F46767"))
                tvNameCheck.isVisible = true
                validName = false

                val inputName = editTextSignupName.text.toString()

                if (inputName.isBlank()) {
                    tvNameCheck.text = getString(R.string.error_empty)
                } else if (regSpecial.containsMatchIn(inputName)) {
                    tvNameCheck.text = getString(R.string.check_invalid_name)
                } else {
                    tvNameCheck.text = ""
                    validName = true
                }
            }

            editTextSignupBirthYear -> {

                if (!validBirthYear || !validBirthMonth || !validBirthDay) {
                    tvBirthCheck.isVisible = true
                }
                validBirthYear = false

                val inputBirthYear = editTextSignupBirthYear.text.toString()

                if (inputBirthYear.isBlank()) {
                    tvBirthCheck.text = getString(R.string.error_empty)
                } else if (inputBirthYear.toInt() !in 1900..2023) {
                    tvBirthCheck.text = getString(R.string.check_birth_year)
                } else {
                    tvBirthCheck.text = ""
                    validBirthYear = true
                }
            }

            editTextSignupBirthMonth -> {

                if (!validBirthYear || !validBirthMonth || !validBirthDay) {
                    tvBirthCheck.isVisible = true
                }
                validBirthMonth = false

                val inputBirthMonth = editTextSignupBirthMonth.text.toString()

                if (inputBirthMonth.isBlank()) {
                    tvBirthCheck.text = getString(R.string.error_empty)
                } else if (inputBirthMonth.toInt() !in 1..12) {
                    tvBirthCheck.text = getString(R.string.check_birth_month)
                } else {
                    tvBirthCheck.text = ""
                    validBirthMonth = true
                }

            }

            editTextSignupBirthDay -> {

                if (!validBirthYear || !validBirthMonth || !validBirthDay) {
                    tvBirthCheck.isVisible = true
                }
                validBirthDay = false

                val inputBirthDay = editTextSignupBirthDay.text.toString()

                if (inputBirthDay.isBlank()) {
                    tvBirthCheck.text = getString(R.string.error_empty)
                } else if (inputBirthDay.toInt() !in 1..31) {
                    tvBirthCheck.text = getString(R.string.check_birth_day)
                } else {
                    tvBirthCheck.text = ""
                    validBirthDay = true
                }
            }

            else -> Unit

        }
    }


}