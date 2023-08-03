package com.example.ecomate.ui.chat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ecomate.databinding.ActivityChatDetailBinding

class ChatDetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityChatDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setAdapter()
        setUi()
    }

    private fun setAdapter() {
        binding.chatRv.apply {
            layoutManager = LinearLayoutManager(this.context)

        }
    }
    private fun setUi() {
        binding.apply {
            backBtn.setOnClickListener {
                finish()
            }
            chatInfoBtn.setOnClickListener {

            }
            chatSendBtn.setOnClickListener {

            }
        }
    }
}