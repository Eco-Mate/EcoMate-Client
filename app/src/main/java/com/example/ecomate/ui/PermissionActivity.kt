package com.example.ecomate.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ecomate.databinding.ActivityPermissionBinding
import com.example.ecomate.ui.user.LoginActivity

class PermissionActivity : AppCompatActivity() {
    lateinit var binding: ActivityPermissionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPermissionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUi()
    }

    private fun setUi() {
        binding.apply {
            permissionBtn.setOnClickListener {
                startActivity(Intent(this@PermissionActivity, LoginActivity::class.java))
            }
        }
    }
}