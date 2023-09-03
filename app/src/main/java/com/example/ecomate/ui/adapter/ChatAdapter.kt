package com.example.ecomate.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.ecomate.databinding.ItemChatBinding
import com.example.ecomate.model.ChatInfoItem

class ChatAdapter :
    ListAdapter<ChatInfoItem, ChatAdapter.ChatViewHolder>(
        ChatDiffCallback()
    ) {
    private lateinit var binding: ItemChatBinding


    inner class ChatViewHolder(private val binding: ItemChatBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setBind(chat: ChatInfoItem) {
            binding.apply {
                chatRoomName.text = chat.name
                var chatMembers = ""
                chat.memberNicknameList.forEach {
                    chatMembers = chatMembers + it + ", "
                }
                members.text = chatMembers
                chatMore.setOnClickListener {
                    popUpChatListener.onClick(it,chat.roomId)
                }
                root.setOnClickListener {
                    detailChatListener.onClick(
                        chat
                    )
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        binding = ItemChatBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ChatViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        holder.setBind(getItem(position))
    }

    interface DetailChatListener {
        fun onClick(chatInfoItem: ChatInfoItem)
    }

    lateinit var detailChatListener: DetailChatListener

    interface PopUpChatListener {
        fun onClick(view: View, roomId: Int)
    }

    lateinit var popUpChatListener: PopUpChatListener


}

class ChatDiffCallback : DiffUtil.ItemCallback<ChatInfoItem>() {
    override fun areItemsTheSame(oldItem: ChatInfoItem, newItem: ChatInfoItem): Boolean {
        return oldItem.roomId == newItem.roomId
    }

    override fun areContentsTheSame(oldItem: ChatInfoItem, newItem: ChatInfoItem): Boolean {
        return oldItem == newItem
    }
}