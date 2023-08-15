package com.example.ecomate.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ecomate.databinding.ItemFollowUserBinding
import com.example.ecomate.model.User

class FollowInfoAdapter :
    ListAdapter<User, FollowInfoAdapter.FollowInfoViewHolder>(
        FollowInfoDiffCallback()
    ) {
    private lateinit var binding: ItemFollowUserBinding

    inner class FollowInfoViewHolder(private val binding: ItemFollowUserBinding) :
    RecyclerView.ViewHolder(binding.root) {
        fun setBind(user: User) {
            binding.apply {
                if (user.image != null && user.image != "") {
                    Glide.with(this.root)
                        .load(user.image)
                        .into(userImg)
                }
                userNickname.text = user.nickname
                followingBtn.setOnClickListener {

                }
                root.setOnClickListener {
                    detailFollowInfoListener.onClick(userId = user.userId)
                }
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowInfoViewHolder {
        binding = ItemFollowUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FollowInfoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FollowInfoViewHolder, position: Int) {
        holder.setBind(getItem(position))
    }

    interface DetailFollowInfoListener {
        fun onClick(userId: Int)
    }

    lateinit var detailFollowInfoListener: DetailFollowInfoListener
}

class FollowInfoDiffCallback : DiffUtil.ItemCallback<User>() {
    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.userId == newItem.userId
    }

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem == newItem
    }
}