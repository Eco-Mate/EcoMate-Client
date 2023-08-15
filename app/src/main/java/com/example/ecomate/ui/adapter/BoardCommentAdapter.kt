package com.example.ecomate.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.ecomate.databinding.ItemCommentBinding
import com.example.ecomate.model.Comment

class BoardCommentAdapter :
    ListAdapter<Comment, BoardCommentAdapter.BoardCommentViewHolder>(
    BoardCommentDiffCallback()
) {
    private lateinit var binding: ItemCommentBinding

    inner class BoardCommentViewHolder(private val binding: ItemCommentBinding) :
    RecyclerView.ViewHolder(binding.root) {
        fun setBind(comment: Comment) {
            binding.apply {
                profileNickname.text = comment.nickname
                commentContent.text = comment.content
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BoardCommentViewHolder {
        binding = ItemCommentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BoardCommentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BoardCommentViewHolder, position: Int) {
        holder.setBind(getItem(position))
    }
}

class BoardCommentDiffCallback : DiffUtil.ItemCallback<Comment>() {
    override fun areItemsTheSame(oldItem: Comment, newItem: Comment): Boolean {
        return oldItem.commentId == newItem.commentId
    }

    override fun areContentsTheSame(oldItem: Comment, newItem: Comment): Boolean {
        return oldItem == newItem
    }
}