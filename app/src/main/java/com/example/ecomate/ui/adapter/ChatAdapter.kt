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
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.example.ecomate.R
import com.example.ecomate.databinding.AccessDialogBinding
import com.example.ecomate.databinding.ChatEditDialogBinding
import com.example.ecomate.databinding.ChatRemoveDialogBinding
import com.example.ecomate.databinding.ItemChatBinding
import com.example.ecomate.model.Chat

class ChatAdapter :
    ListAdapter<Chat, ChatAdapter.ChatViewHolder>(
        ChatDiffCallback()
    ) {
    private lateinit var binding: ItemChatBinding
    private lateinit var chatRemoveDialog: Dialog
    private lateinit var chatEditDialog: Dialog

    inner class ChatViewHolder(private val binding: ItemChatBinding) :
    RecyclerView.ViewHolder(binding.root) {
        fun setBind(chat: Chat) {
            binding.apply {
                chatRoomName.text = chat.name
                var chatMembers = ""
                chat.members.forEach {
                    chatMembers = chatMembers + it.name + ", "
                }
                members.text = chatMembers
                chatMore.setOnClickListener {
                    setPopUpMenu(this.root.context, it)
                }
                root.setOnClickListener {
                    detailChatListener.onClick(
                        chatInfo = chat
                    )
                }
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        binding = ItemChatBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        setChatRemoveDialog(parent)
        setChatEditDialog(parent)
        return ChatViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        holder.setBind(getItem(position))
    }

    interface DetailChatListener {
        fun onClick(chatInfo: Chat)
    }

    lateinit var detailChatListener: DetailChatListener

    private fun setPopUpMenu(context: Context, view: View) {
        val popUp = PopupMenu(context, view)
        popUp.menuInflater.inflate(R.menu.chat_menu, popUp.menu)
        popUp.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.chat_remove -> chatRemoveDialog.show()
                R.id.chat_name_edit -> chatEditDialog.show()
            }
            false
        }
        popUp.show()
    }

    private fun setChatRemoveDialog(parent: ViewGroup) {
        val chatRemoveDialogBinding =
            ChatRemoveDialogBinding.inflate(LayoutInflater.from(parent.context))
        chatRemoveDialog = Dialog(chatRemoveDialogBinding.root.context)

        chatRemoveDialogBinding.apply {
            checkBtn.setOnClickListener {
                chatRemoveDialog.dismiss()
            }
            cancelBtn.setOnClickListener {
                chatRemoveDialog.dismiss()
            }
        }
        chatRemoveDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        chatRemoveDialog.setContentView(chatRemoveDialogBinding.root)
    }

    private fun setChatEditDialog(parent: ViewGroup) {
        val chatEditDialogBinding =
            ChatEditDialogBinding.inflate(LayoutInflater.from(parent.context))
        chatEditDialog = Dialog(chatEditDialogBinding.root.context)

        chatEditDialogBinding.apply {
            checkBtn.setOnClickListener {
                chatEditDialog.dismiss()
            }
            cancelBtn.setOnClickListener {
                chatEditDialog.dismiss()
            }
        }
        chatEditDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        chatEditDialog.setContentView(chatEditDialogBinding.root)
    }
}

class ChatDiffCallback : DiffUtil.ItemCallback<Chat>() {
    override fun areItemsTheSame(oldItem: Chat, newItem: Chat): Boolean {
        return oldItem.chatId == newItem.chatId
    }

    override fun areContentsTheSame(oldItem: Chat, newItem: Chat): Boolean {
        return oldItem == newItem
    }
}