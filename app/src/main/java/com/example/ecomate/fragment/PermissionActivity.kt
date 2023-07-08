package com.example.ecomate.fragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ecomate.databinding.ActivityPermissionBinding

class PermissionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityPermissionBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}