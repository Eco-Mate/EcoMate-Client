package com.example.ecomate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ecomate.databinding.ActivityPostSearchBinding

class PostSearchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityPostSearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}