package com.example.ecomate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ecomate.databinding.ActivityPostAddBinding

class PostAddActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityPostAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}