package com.example.ecomate.ui.chat

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ecomate.databinding.FragmentChatBinding
import com.example.ecomate.model.ChatInfoItem
import com.example.ecomate.ui.adapter.ChatAdapter
import com.example.ecomate.viewmodel.ChatViewModel

class ChatFragment : Fragment() {
    lateinit var binding: FragmentChatBinding
    val chatViewModel: ChatViewModel by viewModels()
//    var chatList: List<Chat> = mutableListOf(
//        Chat(
//            0,
//            "",
//            "지구지키기 방범대 1번 방",
//            mutableListOf(
//                ChatMember(0,"","짱구"),
//                ChatMember(1,"","철수"),
//                ChatMember(2,"","유리"),
//                ChatMember(3,"","훈이"),
//                ChatMember(4,"","맹구"),
//            )),
//        Chat(
//            1,
//            "",
//            "지구지키기 방범대 2번 방",
//            mutableListOf(
//                ChatMember(0,"","짱구"),
//                ChatMember(1,"","철구"),
//                ChatMember(2,"","유수"),
//                ChatMember(3,"","훈리"),
//                ChatMember(4,"","맹이"),
//            )),
//
//    )

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

    override fun onResume() {
        super.onResume()
        chatViewModel.getChatList()
    }

    private fun setAdapter(view: View) {
        val chatAdapter = ChatAdapter()
        chatAdapter.detailChatListener =
            object : ChatAdapter.DetailChatListener {
                override fun onClick(chatInfoItem: ChatInfoItem) {
                    val intent = Intent(activity, ChatDetailActivity::class.java)
                    intent.putExtra("chatInfoItem", chatInfoItem)
                    startActivity(intent)
                }
            }

        binding.chatRv.apply {
            layoutManager = LinearLayoutManager(view.context)
            adapter = chatAdapter
        }
        chatViewModel.chatList.observe(viewLifecycleOwner) {
            chatAdapter.submitList(it)
        }

    }

    private fun setUi() {
        binding.apply {
            chatAdd.setOnClickListener {
                startActivity(Intent(activity, ChatAddActivity::class.java))
            }
        }
    }
}