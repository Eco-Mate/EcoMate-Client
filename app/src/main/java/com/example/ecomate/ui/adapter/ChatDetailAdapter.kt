package com.example.ecomate.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.ecomate.databinding.ItemChatDetailBinding
import com.example.ecomate.model.Chat
import com.example.ecomate.ui.util.Util.loadImg


class ChatDetailAdapter :
    ListAdapter<Chat, ChatDetailAdapter.ChatDetailViewHolder>(
        ChatDetailDiffCallback()
    ) {
    private lateinit var binding: ItemChatDetailBinding

    inner class ChatDetailViewHolder(private val binding: ItemChatDetailBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setBind(chat: Chat) {
            binding.apply {
                loadImg(binding.root.context, chat.profileImage.toString(), chatDetailIv)
                chatDetailName.text = chat.senderNickname
                chatDetailMessage.text = chat.message
                chatDetailDate.text = chat.createdTime
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatDetailViewHolder {
        binding = ItemChatDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ChatDetailViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ChatDetailViewHolder, position: Int) {
        holder.setBind(getItem(position))
    }

//    interface DetailMemberListener {
//        fun onClick(memberId: Int)
//    }
//
//    lateinit var detailMemberListener: DetailMemberListener
}

class ChatDetailDiffCallback : DiffUtil.ItemCallback<Chat>() {
    override fun areItemsTheSame(oldItem: Chat, newItem: Chat): Boolean {
        return oldItem.chatId == newItem.chatId
    }

    override fun areContentsTheSame(oldItem: Chat, newItem: Chat): Boolean {
        return oldItem == newItem
    }
}