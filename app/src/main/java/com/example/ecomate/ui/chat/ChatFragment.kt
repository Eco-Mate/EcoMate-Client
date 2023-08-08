package com.example.ecomate.ui.chat

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ecomate.databinding.FragmentChatBinding
import com.example.ecomate.model.Chat
import com.example.ecomate.ui.adapter.ChatAdapter

class ChatFragment : Fragment() {
    lateinit var binding: FragmentChatBinding
    var chatList: List<Chat> = mutableListOf(
        Chat(0,"","지구지키기 방범대 1번 방", mutableListOf("짱구","철수","유리","훈이","맹구")),
        Chat(1,"","지구지키기 방범대 2번 방", mutableListOf("짱구","철구","유수","훈리","맹이")),
        Chat(2,"","지구지키기 방범대 3번 방", mutableListOf("짱이","철구","유구","훈수","맹리")),
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChatBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter(view)
        setUi()
    }

    private fun setAdapter(view: View) {
        val chatAdapter = ChatAdapter(chatList)
        chatAdapter.detailChatListener =
            object : ChatAdapter.DetailChatListener {
                override fun onClick(chatId: Int) {
                    val intent = Intent(activity, ChatDetailActivity::class.java)
                    intent.putExtra("chatId", chatId)
                    startActivity(intent)
                }
            }

        binding.chatRv.apply {
            layoutManager = LinearLayoutManager(view.context)
            adapter = chatAdapter
        }
    }
    private fun setUi() {
        binding.apply {
            chatAdd.setOnClickListener {
                startActivity(Intent(activity,ChatAddActivity::class.java))
            }
        }
    }
}