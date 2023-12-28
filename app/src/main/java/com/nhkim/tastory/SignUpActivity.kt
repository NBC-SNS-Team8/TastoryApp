package com.nhkim.tastory

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import androidx.constraintlayout.widget.ConstraintLayout

class SignUpActivity : AppCompatActivity() {

    private val btnReturn: ConstraintLayout by lazy {
        findViewById(R.id.cl_signup_return)
    }
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
    private val btnSignup: ConstraintLayout by lazy {
        findViewById(R.id.cl_signup_btn)
    }

    private val etList = listOf(editTextSignupId, editTextSignupPw, editTextSignupName, editTextSignupBirthYear, editTextSignupBirthMonth, editTextSignupBirthDay)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        initView()

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


    }


}