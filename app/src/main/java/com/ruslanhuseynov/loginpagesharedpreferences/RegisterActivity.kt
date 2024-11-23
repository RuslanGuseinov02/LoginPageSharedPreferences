package com.ruslanhuseynov.loginpagesharedpreferences

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.ruslanhuseynov.loginpagesharedpreferences.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.buttonSignUp.setOnClickListener {

            val email = binding.emailUpView.text.toString().trim()
            val pass = binding.passUpView.text.toString().trim()

            if (email.isNotEmpty() && pass.isNotEmpty()){

                val shared = getSharedPreferences("login", MODE_PRIVATE)
                val edit = shared.edit()

                edit.putString("email",email)
                edit.putString("pass",pass)

                edit.apply()

                Toast.makeText(this@RegisterActivity,"Account Created",Toast.LENGTH_SHORT).show()
                val intent = Intent(this@RegisterActivity,MainActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                finish()
                startActivity(intent)
            }else{
                Toast.makeText(this@RegisterActivity,"Enter Email And Password",Toast.LENGTH_SHORT).show()
            }
        }
    }
}