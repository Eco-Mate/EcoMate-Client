package com.example.ecomate.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ecomate.ApplicationClass
import com.example.ecomate.R
import com.example.ecomate.databinding.ItemFollowUserBinding
import com.example.ecomate.model.Board
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
                moreBtn.setOnClickListener {
                    setPopUpMenu(this.root.context, it, user)
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

    interface DetailUserProfileListener {
        fun onClick(memberId: Int)
    }

    lateinit var detailUserProfileListener: DetailUserProfileListener

    private fun setPopUpMenu(context: Context, view: View, user: User) {
        val popUp = PopupMenu(context, view)
        popUp.menuInflater.inflate(R.menu.follow_more_menu, popUp.menu)
        popUp.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.profile_move -> detailUserProfileListener.onClick(memberId = user.memberId)
            }
            false
        }
        popUp.show()
    }
}

class FollowInfoDiffCallback : DiffUtil.ItemCallback<User>() {
    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.memberId == newItem.memberId
    }

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem == newItem
    }
}