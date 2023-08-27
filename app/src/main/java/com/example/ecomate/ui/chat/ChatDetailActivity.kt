package com.example.ecomate.ui.chat

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.ecomate.databinding.ActivityChatDetailBinding
import com.example.ecomate.viewmodel.ChatDetailViewModel

class ChatDetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityChatDetailBinding
    var chatItem: Int = 0
    val chatDetailViewModel : ChatDetailViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        chatItem = intent.getIntExtra("roomId", 0)

        Log.e("chatItem",chatItem.toString())

        chatDetailViewModel.runStomp(chatItem)


        binding.chatSendBtn.setOnClickListener {
            chatDetailViewModel.sendStomp(binding.chatEt.text.toString(),chatItem)
        }

//
//        setAdapter()
//        setUi()
    }


    override fun onDestroy() {
        super.onDestroy()
        chatDetailViewModel.stompClient.disconnect()
    }



//    private fun setAdapter() {
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
//        chatMemberAdapter.submitList(chatItem.members)
//    }
//
//    private fun setUi() {
//        binding.apply {
//            toolbarTitle.text = chatItem.name
//            roomName.text = chatItem.name
//            backBtn.setOnClickListener {
//                finish()
//            }
//            chatInfoBtn.setOnClickListener {
//                if (!drawer.isDrawerOpen(Gravity.RIGHT)) {
//                    drawer.openDrawer(Gravity.RIGHT)
//                }
//            }
//            chatSendBtn.setOnClickListener {
//
//            }
//            drawerBackBtn.setOnClickListener {
//                if (drawer.isDrawerOpen(Gravity.RIGHT)) {
//                    drawer.closeDrawer(Gravity.RIGHT)
//                }
//            }
//            memberAddBtn.setOnClickListener {
//                startActivity(Intent(this@ChatDetailActivity, ChatAddActivity::class.java))
//            }
//
//        }
//    }
}