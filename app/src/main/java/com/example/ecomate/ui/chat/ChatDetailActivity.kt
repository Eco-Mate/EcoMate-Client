package com.example.ecomate.ui.chat

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ecomate.databinding.ActivityChatDetailBinding
import com.example.ecomate.model.ChatInfoItem
import com.example.ecomate.ui.adapter.ChatDetailAdapter
import com.example.ecomate.viewmodel.ChatDetailViewModel

class ChatDetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityChatDetailBinding
    lateinit var chatInfoItem: ChatInfoItem
    val chatDetailViewModel: ChatDetailViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        chatInfoItem = intent.getSerializableExtra("chatInfoItem") as ChatInfoItem

        chatDetailViewModel.getChatDetail(chatInfoItem.roomId)
        chatDetailViewModel.runStomp(chatInfoItem.roomId)

        binding.chatSendBtn.setOnClickListener {
            chatDetailViewModel.sendStomp(binding.chatEt.text.toString(), chatInfoItem.roomId)
            binding.chatEt.setText("")
        }

        setAdapter()
        setUi()
    }


    override fun onDestroy() {
        super.onDestroy()
        chatDetailViewModel.stompClient.disconnect()
    }

    private fun setAdapter() {
        val chatDetailAdapter = ChatDetailAdapter()

        binding.apply {
            chatRv.apply {
                layoutManager = LinearLayoutManager(this.context)
                adapter = chatDetailAdapter
            }
        }
        chatDetailViewModel.chatDetail.observe(this) {
            chatDetailAdapter.submitList(it.toMutableList())
        }


//        val chatMemberAdapter = ChatMemberAdapter()
//        chatMemberAdapter.detailMemberListener =
//            object : ChatMemberAdapter.DetailMemberListener {
//                override fun onClick(memberId: Int) {
//                    Toast.makeText(binding.root.context, "$memberId Clicked!", Toast.LENGTH_SHORT)
//                        .show()
//                }
//            }
//        binding.apply {
//            chatRv.apply {
//                layoutManager = LinearLayoutManager(this.context)
//
//            }
//            memberRv.apply {
//                layoutManager = LinearLayoutManager(this.context)
//                adapter = chatMemberAdapter
//            }
//        }
//
//        chatDetailViewModel.chatDetail.observe(this){
//            chatMemberAdapter.submitList(it.toMutableList())
//        }

        //chatMemberAdapter.submitList(chatItem.members)
    }

    private fun setUi() {
        binding.apply {
            toolbarTitle.text = chatInfoItem.name
            roomName.text = chatInfoItem.name
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