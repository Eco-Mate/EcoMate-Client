package com.example.ecomate.ui.chat

import android.content.Intent
import android.graphics.Color
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
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.utils.ColorTemplate

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
        chatDetailViewModel.getChatMemberInfo(chatInfoItem.roomId)
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
        chatDetailViewModel.chatMemberInfo.observe(this){
            val dataSet = PieDataSet(it,"")
            dataSet.colors = ColorTemplate.COLORFUL_COLORS.toList()
            val pieDate = PieData(dataSet)
            binding.challengeState.data = pieDate
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

            challengeState.description.isEnabled = false
//            challengeState.isRotationEnabled = true//회전가능
            challengeState.setUsePercentValues(true)//백분율
            challengeState.setEntryLabelTextSize(12f)//text 크기
            challengeState.setEntryLabelColor(Color.BLACK)//라벨 텍스트 색깔
//            challengeState.animateY(1000)//애니매이션이라고 하는데??

        }
    }
}