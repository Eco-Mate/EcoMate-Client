package com.example.ecomate.ui.chat

import android.R
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ecomate.databinding.ActivityChatAddBinding
import com.example.ecomate.ui.LoadingDialog
import com.example.ecomate.ui.adapter.ChatMemberAdapter
import com.example.ecomate.viewmodel.ChatAddViewModel

class ChatAddActivity : AppCompatActivity() {
    lateinit var binding: ActivityChatAddBinding
    val chatAddViewModel: ChatAddViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatAddBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUi()
        setAdapter()

    }

    private fun setAdapter() {
        chatAddViewModel.nickNameList.observe(this) {
            val adapter = ArrayAdapter<String>(
                this,
                R.layout.simple_dropdown_item_1line,
                it
            )
            binding.memberSearchView.setAdapter(adapter)//로직이 별로긴함 실시간 확인이 아님
        }

        val chatMemberAdapter = ChatMemberAdapter()
        chatMemberAdapter.deleteMemberListener =
            object : ChatMemberAdapter.DeleteMemberListener {
                override fun onClick(position: Int) {
                    chatAddViewModel.memberList.removeAt(position)
                    chatMemberAdapter.submitList(chatAddViewModel.memberList.toMutableList())
                }
            }
        binding.memberSearchView.setOnItemClickListener { adapterView, view, i, l ->

            chatAddViewModel.memberList.add(adapterView.getItemAtPosition(i).toString())
            binding.memberSearchView.setText("")
            chatMemberAdapter.submitList(chatAddViewModel.memberList.toMutableList())
        }



        binding.addNickNameRv.apply {
            layoutManager = LinearLayoutManager(this@ChatAddActivity)
        }
        binding.addNickNameRv.adapter = chatMemberAdapter
    }

    private fun setUi() {

        val dialog = LoadingDialog(this)
        chatAddViewModel.isLoading.observe(this) {
            if (chatAddViewModel.isLoading.value!!) {
                dialog.show()
            } else if (!chatAddViewModel.isLoading.value!!) {
                dialog.dismiss()
            }
        }


        binding.apply {
            backBtn.setOnClickListener {
                finish()
            }
            chatAddBtn.setOnClickListener {
                chatAddViewModel.postChat(this@ChatAddActivity)
            }
        }
    }
}