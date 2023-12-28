package com.nhkim.tastory

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.constraintlayout.widget.ConstraintLayout

lateinit var activityResultLauncher: ActivityResultLauncher<Intent>
class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        
        val editTextLoginId = findViewById<EditText>(R.id.et_login_id)
        val editTextLoginPw = findViewById<EditText>(R.id.et_login_pw)
        val btnLogin = findViewById<ConstraintLayout>(R.id.cl_login_btn)
        val btnSignupPage = findViewById<ConstraintLayout>(R.id.cl_login_signup_btn)
        
        //activityResultLauncher
        
        btnLogin.setOnClickListener {
            if (editTextLoginId.text.trim().isEmpty() || editTextLoginPw.text.trim().isEmpty()) {
                Toast.makeText(this, getString(R.string.toast_empty), Toast.LENGTH_SHORT).show()
            }
        }
        
        btnSignupPage.setOnClickListener { 
            
        }
        
        
    }
}