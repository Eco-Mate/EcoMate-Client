package com.example.ecomate.ui.community

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