package com.nhkim.tastory

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.constraintlayout.widget.ConstraintLayout

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val btnReturn = findViewById<ConstraintLayout>(R.id.cl_signup_return)
        val editTextSignupId = findViewById<ConstraintLayout>(R.id.cl_signup_id)
        val editTextSignupPw = findViewById<ConstraintLayout>(R.id.cl_signup_pw)
        val editTextSignupName = findViewById<ConstraintLayout>(R.id.cl_signup_name)
        val editTextSignupBirth = findViewById<ConstraintLayout>(R.id.cl_signup_birth)
        val btnSignup = findViewById<ConstraintLayout>(R.id.cl_signup_btn)

        btnReturn.setOnClickListener {

        }

        btnSignup.setOnClickListener {

        }


    }
}