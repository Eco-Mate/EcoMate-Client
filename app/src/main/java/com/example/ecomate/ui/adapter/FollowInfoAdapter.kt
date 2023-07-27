package com.example.ecomate.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ecomate.databinding.ItemFollowUserBinding
import com.example.ecomate.model.User

class FollowInfoViewHolder(val binding: ItemFollowUserBinding): RecyclerView.ViewHolder(binding.root)

class FollowInfoAdapter(val dataSet: List<User>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private lateinit var binding: ItemFollowUserBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        binding = ItemFollowUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FollowInfoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        binding.apply {
            userNickname.text = dataSet[position].nickname
            followingBtn.setOnClickListener {

            }
        }
    }

    override fun getItemCount(): Int {
        Log.d("RecyclerView", "init data size: ${dataSet.size}")
        return dataSet.size
    }
}