package com.ruslanhuseynov.loginpagesharedpreferences

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.ruslanhuseynov.loginpagesharedpreferences.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding
    private lateinit var shared: SharedPreferences

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        shared = getSharedPreferences("login", MODE_PRIVATE)

        val email = shared.getString("email", "Not Found")
        val pass = shared.getString("pass", "Not Found")

        binding.emailResultView.text = "Email: $email"
        binding.passResultView.text = "Password: $pass"

        binding.buttonSignOut.setOnClickListener {

            val editor = shared.edit()
            editor.putBoolean("logIn",false)
            editor.apply()

            Toast.makeText(this@ResultActivity, "Signed Out", Toast.LENGTH_SHORT).show()

            val outIntent = Intent(this@ResultActivity, MainActivity::class.java)
            outIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            finish()
            startActivity(outIntent)
        }
    }
}
