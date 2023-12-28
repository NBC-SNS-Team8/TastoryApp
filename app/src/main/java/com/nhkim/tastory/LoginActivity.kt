package com.nhkim.tastory

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.widget.addTextChangedListener

//lateinit var activityResultLauncher: ActivityResultLauncher<Intent>

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

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initView()

        //activityResultLauncher
        
        btnLogin.setOnClickListener {
            if (editTextLoginId.text.trim().isEmpty() || editTextLoginPw.text.trim().isEmpty()) {
                Toast.makeText(this, getString(R.string.toast_empty), Toast.LENGTH_SHORT).show()
            }
        }
        
        btnSignupPage.setOnClickListener { 
            var intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
        
        
    }

    private fun initView() {
        editTextLoginId.addTextChangedListener {

        }

        editTextLoginId.setOnFocusChangeListener { v, hasFocus ->

        }


    }

}