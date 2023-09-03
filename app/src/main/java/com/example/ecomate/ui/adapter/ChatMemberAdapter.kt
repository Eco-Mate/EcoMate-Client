package com.example.ecomate.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.ecomate.databinding.ItemChatMemberBinding

class ChatMemberAdapter :
    ListAdapter<String, ChatMemberAdapter.ChatMemberViewHolder>(
        ChatMemberDiffCallback()
    ) {
    private lateinit var binding: ItemChatMemberBinding

    inner class ChatMemberViewHolder(private val binding: ItemChatMemberBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setBind(nickname: String) {
            binding.apply {
                userName.text = nickname
                chatMore.setOnClickListener {
                    deleteMemberListener.onClick(adapterPosition)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatMemberViewHolder {
        binding = ItemChatMemberBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ChatMemberViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ChatMemberViewHolder, position: Int) {
        holder.setBind(getItem(position))
    }

    interface DeleteMemberListener {
        fun onClick(position: Int)
    }

    lateinit var deleteMemberListener: DeleteMemberListener
}

class ChatMemberDiffCallback : DiffUtil.ItemCallback<String>() {
    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }
}