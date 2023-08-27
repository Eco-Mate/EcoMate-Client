package com.example.ecomate.ui.adapter

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ecomate.ApplicationClass.Companion.sharedPreferencesUtil
import com.example.ecomate.R
import com.example.ecomate.databinding.ItemBoardBinding
import com.example.ecomate.databinding.ItemUserBoardBinding
import com.example.ecomate.model.Board

class UserBoardsAdapter : ListAdapter<Board, UserBoardsAdapter.UserBoardsViewHolder>(
    UserBoardsDiffCallback()
) {
    private lateinit var binding: ItemUserBoardBinding

    inner class UserBoardsViewHolder(private val binding: ItemUserBoardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @RequiresApi(Build.VERSION_CODES.Q)
        fun setBind(board: Board) {
            binding.apply {
                postTitle.text = board.boardTitle
                postContent.text = board.boardContent
                postDate.text = board.createdDate.substring(0, 4) +
                        "." + board.createdDate.substring(5, 7) +
                        "." + board.createdDate.substring(8, 10)
                postLike.text = "좋아요 ${board.likeCnt}"
                if (board.image != null && board.image != "") {
                    Glide.with(this.root.context)
                        .load(board.image)
                        .into(postImage)
                }
                root.setOnClickListener { 
                    detailBoardListener.onClick(board = board)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserBoardsViewHolder {
        binding = ItemUserBoardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserBoardsViewHolder(binding)
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onBindViewHolder(holder: UserBoardsViewHolder, position: Int) {
        holder.setBind(getItem(position))
    }

    interface DetailBoardListener {
        fun onClick(board: Board)
    }

    lateinit var detailBoardListener: DetailBoardListener
}

class UserBoardsDiffCallback : DiffUtil.ItemCallback<Board>() {
    override fun areItemsTheSame(oldItem: Board, newItem: Board): Boolean {
        return oldItem.boardId == newItem.boardId
    }

    override fun areContentsTheSame(oldItem: Board, newItem: Board): Boolean {
        return oldItem == newItem
    }
}