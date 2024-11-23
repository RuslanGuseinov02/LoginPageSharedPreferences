package com.ruslanhuseynov.loginpagesharedpreferences

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.ruslanhuseynov.loginpagesharedpreferences.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var shared: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        shared = getSharedPreferences("login", MODE_PRIVATE)

        val logIn = shared.getBoolean("logIn", false)

        if (logIn) {
            val intent = Intent(this@MainActivity, ResultActivity::class.java)
            finish()
            startActivity(intent)
        }

        binding.buttonSignIn.setOnClickListener {

            val email = binding.emailView.text.toString().trim()
            val pass = binding.passView.text.toString().trim()

            if (email.isNotEmpty() && pass.isNotEmpty()) {

                val savedEmail = shared.getString("email", "")
                val savedPass = shared.getString("pass", "")

                if (email == savedEmail && pass == savedPass) {

                    val editor = shared.edit()
                    editor.putBoolean("logIn",true)
                    editor.apply()

                    val intent = Intent(this@MainActivity, ResultActivity::class.java)
                    finish()
                    startActivity(intent)
                }else{

                    Toast.makeText(this@MainActivity, "Email or Password is incorrect", Toast.LENGTH_SHORT).show()
                }

            }else{
                Toast.makeText(this@MainActivity, "Please enter Email and Password", Toast.LENGTH_SHORT).show()
            }
        }

        binding.accountView.setOnClickListener {
            val intent = Intent(this@MainActivity, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}
