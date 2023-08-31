package com.example.ecomate.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ecomate.ApplicationClass
import com.example.ecomate.R
import com.example.ecomate.databinding.ItemChatReceiveBinding
import com.example.ecomate.databinding.ItemChatSendBinding
import com.example.ecomate.model.Chat


class ChatDetailAdapter :
    ListAdapter<Chat, RecyclerView.ViewHolder>(
        ChatDetailDiffCallback()
    ) {
    private val holderTypeMessageReceived = 1
    private val holderTypeMessageSent = 2

    inner class ReceivedViewHolder(private val binding: ItemChatReceiveBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Chat) {
            Glide.with(binding.root.context)
                .load(item.profileImage)
                .fallback(R.drawable.baseline_delete_24)
                .circleCrop()
                .into(binding.receiveIv)
            binding.receiveNickname.text = item.senderNickname
            binding.receiveMessageText.text = item.message
        }
    }

    inner class SentViewHolder(private val binding: ItemChatSendBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Chat) {
            binding.sendMessageText.text = item.message
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (getItem(position).senderId != ApplicationClass.sharedPreferencesUtil.getMemberId()) {
            holderTypeMessageReceived
        } else {
            holderTypeMessageSent
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        return when (viewType) {
            holderTypeMessageSent -> {
                val binding = ItemChatSendBinding.inflate(layoutInflater, parent, false)
                SentViewHolder(binding)
            }

            holderTypeMessageReceived -> {
                val binding = ItemChatReceiveBinding.inflate(layoutInflater, parent, false)
                ReceivedViewHolder(binding)
            }

            else -> {
                throw Exception("Error reading holder type")
            }
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            holderTypeMessageSent -> (holder as SentViewHolder).bind(
                getItem(position)
            )

            holderTypeMessageReceived -> (holder as ReceivedViewHolder).bind(
                getItem(position)
            )
        }
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