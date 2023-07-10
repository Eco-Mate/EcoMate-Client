package com.example.ecomate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ecomate.databinding.ActivityPostInfoBinding

class PostInfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityPostInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}