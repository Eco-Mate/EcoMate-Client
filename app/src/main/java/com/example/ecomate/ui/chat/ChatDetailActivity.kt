package com.example.ecomate.ui.chat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ecomate.ApplicationClass.Companion.CHAT_ITEM
import com.example.ecomate.databinding.ActivityChatDetailBinding
import com.example.ecomate.model.Chat
import com.example.ecomate.ui.adapter.ChatMemberAdapter

class ChatDetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityChatDetailBinding
    lateinit var chatItem: Chat

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        chatItem = intent.getSerializableExtra(CHAT_ITEM) as Chat
        setAdapter()
        setUi()
    }

    private fun setAdapter() {
        val chatMemberAdapter = ChatMemberAdapter()
        chatMemberAdapter.detailMemberListener =
            object : ChatMemberAdapter.DetailMemberListener {
                override fun onClick(memberId: Int) {
                    Toast.makeText(binding.root.context, "$memberId Clicked!", Toast.LENGTH_SHORT).show()
                }
            }
        binding.apply {
            chatRv.apply {
                layoutManager = LinearLayoutManager(this.context)

            }
            memberRv.apply {
                layoutManager = LinearLayoutManager(this.context)
                adapter = chatMemberAdapter
            }
        }

        chatMemberAdapter.submitList(chatItem.members)
    }
    private fun setUi() {
        binding.apply {
            toolbarTitle.text = chatItem.name
            roomName.text = chatItem.name
            backBtn.setOnClickListener {
                finish()
            }
            chatInfoBtn.setOnClickListener {
                if (!drawer.isDrawerOpen(Gravity.RIGHT)) {
                    drawer.openDrawer(Gravity.RIGHT)
                }
            }
            chatSendBtn.setOnClickListener {

            }
            drawerBackBtn.setOnClickListener {
                if (drawer.isDrawerOpen(Gravity.RIGHT)) {
                    drawer.closeDrawer(Gravity.RIGHT)
                }
            }
            memberAddBtn.setOnClickListener {
                startActivity(Intent(this@ChatDetailActivity, ChatAddActivity::class.java))
            }

        }
    }
}