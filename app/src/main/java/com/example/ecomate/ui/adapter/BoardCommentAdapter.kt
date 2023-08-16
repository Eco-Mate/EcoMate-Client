package com.example.ecomate.ui.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ecomate.ApplicationClass.Companion.sharedPreferencesUtil
import com.example.ecomate.R
import com.example.ecomate.databinding.ItemCommentBinding
import com.example.ecomate.model.Board
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
                if (comment.profileImage != null && comment.profileImage != "") {
                    Glide.with(this.root)
                        .load(comment.profileImage)
                        .into(profileImg)
                }
                profileNickname.text = comment.nickname
                commentDate.text = comment.createdDate.substring(0, 4) +
                        "." + comment.createdDate.substring(5, 7) +
                        "." + comment.createdDate.substring(8, 10)
                commentContent.text = comment.content
                commentMore.setOnClickListener {
                    setPopUpMenu(this.root.context, it, comment)
                }
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

    interface DetailCommentListener {
        fun onClick(comment: Comment)
    }

    lateinit var detailCommentListener: DetailCommentListener

    private fun setPopUpMenu(context: Context, view: View, comment: Comment) {
        val popUp = PopupMenu(context, view)
        popUp.menuInflater.inflate(R.menu.comment_menu, popUp.menu)
        if (sharedPreferencesUtil.getMemberId() != comment.memberId) {
            popUp.menu.removeItem(R.id.comment_remove)
        }
        popUp.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.profile_info -> Toast.makeText(context, "프로필 정보 이동", Toast.LENGTH_SHORT).show()
                R.id.comment_remove -> detailCommentListener.onClick(comment = comment)
            }
            false
        }
        popUp.show()
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