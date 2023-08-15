package com.example.ecomate.ui.adapter

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.PopupMenu
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ecomate.R
import com.example.ecomate.databinding.AccessDialogBinding
import com.example.ecomate.databinding.ChatEditDialogBinding
import com.example.ecomate.databinding.ChatRemoveDialogBinding
import com.example.ecomate.databinding.ItemChatBinding
import com.example.ecomate.databinding.ItemChatMemberBinding
import com.example.ecomate.model.Chat
import com.example.ecomate.model.ChatMember

class ChatMemberAdapter :
    ListAdapter<ChatMember, ChatMemberAdapter.ChatMemberViewHolder>(
        ChatMemberDiffCallback()
    ) {
    private lateinit var binding: ItemChatMemberBinding

    inner class ChatMemberViewHolder(private val binding: ItemChatMemberBinding) :
    RecyclerView.ViewHolder(binding.root) {
        fun setBind(chatMember: ChatMember) {
            binding.apply {
                if (chatMember.image != null && chatMember.image != "") {
                    Glide.with(this.root)
                        .load(chatMember.image)
                        .into(userImage)
                }
                userName.text = chatMember.name
                root.setOnClickListener {
                    detailMemberListener.onClick(
                        memberId = chatMember.memberId
                    )
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

    interface DetailMemberListener {
        fun onClick(memberId: Int)
    }

    lateinit var detailMemberListener: DetailMemberListener
}

class ChatMemberDiffCallback : DiffUtil.ItemCallback<ChatMember>() {
    override fun areItemsTheSame(oldItem: ChatMember, newItem: ChatMember): Boolean {
        return oldItem.memberId == newItem.memberId
    }

    override fun areContentsTheSame(oldItem: ChatMember, newItem: ChatMember): Boolean {
        return oldItem == newItem
    }
}