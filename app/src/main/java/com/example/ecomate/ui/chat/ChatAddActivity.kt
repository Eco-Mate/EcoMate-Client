package com.example.ecomate.ui.chat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ecomate.databinding.ActivityChatAddBinding
import com.example.ecomate.databinding.ActivityChatDetailBinding

class ChatAddActivity : AppCompatActivity() {
    lateinit var binding: ActivityChatAddBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatAddBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setAdapter()
        setUi()
    }

    private fun setAdapter() {
        binding.followingRv.apply {
            layoutManager = LinearLayoutManager(this@ChatAddActivity)
//            adapter =
        }
        binding.addedRv.apply {
            layoutManager = LinearLayoutManager(this@ChatAddActivity)
//            adapter =
        }
    }
    private fun setUi() {
        binding.apply {
            backBtn.setOnClickListener {
                finish()
            }
            searchBtn.setOnClickListener {

            }
            chatAddBtn.setOnClickListener {
                finish()
            }
        }
    }
}