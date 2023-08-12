package com.example.ecomate.ui.adapter

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.PopupMenu
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.ecomate.R
import com.example.ecomate.databinding.AccessDialogBinding
import com.example.ecomate.databinding.ChatEditDialogBinding
import com.example.ecomate.databinding.ChatRemoveDialogBinding
import com.example.ecomate.databinding.ItemChatBinding
import com.example.ecomate.databinding.ItemChatMemberBinding
import com.example.ecomate.model.Chat
import com.example.ecomate.model.ChatMember

class ChatMemberViewHolder(val binding: ItemChatMemberBinding): RecyclerView.ViewHolder(binding.root)

class ChatMemberAdapter(val dataSet: List<ChatMember>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private lateinit var binding: ItemChatMemberBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        binding = ItemChatMemberBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ChatMemberViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        binding.apply {
            userName.text = dataSet[position].name
            root.setOnClickListener {
                detailMemberListener.onClick(
                    memberId = dataSet[position].memberId
                )
            }
        }

    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    interface DetailMemberListener {
        fun onClick(memberId: Int)
    }

    lateinit var detailMemberListener: DetailMemberListener
}