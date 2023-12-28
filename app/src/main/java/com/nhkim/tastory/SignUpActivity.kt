package com.nhkim.tastory

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import androidx.constraintlayout.widget.ConstraintLayout

class SignUpActivity : AppCompatActivity() {
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

        btnReturn.setOnClickListener {
            finish()
        }

        btnSignup.setOnClickListener {
            val userId = editTextSignupId.text.toString()
            val userPw = editTextSignupPw.text.toString()
            val userName = editTextSignupName.text.toString()
            val userBirth = editTextSignupBirthYear.text.toString() + editTextSignupBirthMonth.text.toString() + editTextSignupBirthDay.text.toString()

            val intent = Intent(this, LoginActivity::class.java).apply {
                putExtra("Id", userId)
                putExtra("Pw", userPw)
                putExtra("Name", userName)
                putExtra("Birth", userBirth)
            }
            setResult(RESULT_OK, intent)
            if (!isFinishing) finish()
        }


    }
}