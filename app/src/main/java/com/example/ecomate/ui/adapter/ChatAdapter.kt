package com.example.ecomate.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ecomate.databinding.ItemChatBinding
import com.example.ecomate.model.Chat

class ChatViewHolder(val binding: ItemChatBinding): RecyclerView.ViewHolder(binding.root)

class ChatAdapter(val dataSet: List<Chat>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private lateinit var binding: ItemChatBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        binding = ItemChatBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ChatViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        binding.apply {
            chatRoomName.text = dataSet[position].name
            members.text = dataSet[position].members.toString().substring(1,dataSet[position].members.size)
            chatMore.setOnClickListener {

            }
            root.setOnClickListener {
                detailChatListener.onClick(chatId = dataSet[position].chatId)
            }
        }

    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    interface DetailChatListener {
        fun onClick(chatId: Int)
    }

    lateinit var detailChatListener: DetailChatListener
}