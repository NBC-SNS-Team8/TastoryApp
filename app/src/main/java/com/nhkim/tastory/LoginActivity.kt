package com.nhkim.tastory

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.widget.addTextChangedListener

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

    private var userId: String = ""
    private var userName: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initView()

        activityResultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                if (it.resultCode == RESULT_OK) {
                    userId = it.data?.getStringExtra("Id") ?: ""
                    val userPw = it.data?.getStringExtra("Pw") ?: ""
                    userName = it.data?.getStringExtra("Name") ?: ""

                    editTextLoginId.setText(userId)
                    editTextLoginPw.setText(userPw)
                }
            }

        btnLogin.setOnClickListener {
            if (editTextLoginId.text.trim().isEmpty() || editTextLoginPw.text.trim().isEmpty()) {
                Toast.makeText(this, getString(R.string.error_empty), Toast.LENGTH_SHORT).show()
            } else {
                val user = User(userId, userName)
                val intent = Intent(this, FeedActivity::class.java).apply {
                    putExtra("USER", user)
                }
                startActivity(intent)

            }

        }

        btnSignupPage.setOnClickListener {
            var intent = Intent(this, SignUpActivity::class.java)
            activityResultLauncher.launch(intent)

        }


    }

    private fun initView() {
        editTextLoginId.addTextChangedListener {
            if (editTextLoginId.text.toString().isNotBlank()) {
                //
            }
        }

        editTextLoginId.setOnFocusChangeListener { v, hasFocus ->

        }


    }

}