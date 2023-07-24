package com.example.ecomate.ui.community

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ecomate.databinding.ActivityBoardAddBinding
class BoardAddActivity : AppCompatActivity() {
    lateinit var binding: ActivityBoardAddBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityBoardAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}