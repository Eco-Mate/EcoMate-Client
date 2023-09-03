package com.example.ecomate.ui.chat

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
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
            Log.e("chatSendBtn", binding.chatEt.text.toString() + chatInfoItem.roomId.toString())
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

                addOnLayoutChangeListener(onLayoutChangeListener)
            }
        }
//        binding.chatRv.addOnLayoutChangeListener{_, _, _, _, bottom, _, _, _, oldBottom ->
//            Log.d("chatRv", "Bottom: $bottom, OldBottom: $oldBottom")
//            if (bottom < oldBottom) {
//                // 키보드가 나타날 때(레이아웃의 하단 위치가 이전보다 작아질 때)
//                binding.chatRv.postDelayed({
//                    binding.chatRv.scrollToPosition(chatDetailAdapter.itemCount - 1)
//                }, 100)
//            }
//        }
        chatDetailViewModel.chatDetail.observe(this) {
            chatDetailAdapter.submitList(it.toMutableList())
            binding.chatRv.scrollToPosition(chatDetailAdapter.itemCount - 1)
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
    private val onLayoutChangeListener =
        View.OnLayoutChangeListener { _, _, _, _, bottom, _, _, _, oldBottom ->
            // 키보드가 올라와 높이가 변함
            if (bottom <= oldBottom) {
                binding.chatRv.scrollBy(0, oldBottom - bottom) // 스크롤 유지를 위해 추가
            }
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