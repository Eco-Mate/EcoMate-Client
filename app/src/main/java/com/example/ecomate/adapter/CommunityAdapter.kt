package com.example.ecomate.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ecomate.databinding.PostItemBinding

class CommunityViewHolder(val binding: PostItemBinding): RecyclerView.ViewHolder(binding.root)

class CommunityAdapter(val dataSet: MutableList<String>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CommunityViewHolder(PostItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        Log.d("RecyclerView", "onBindViewHolder(): $position")
        val binding = (holder as CommunityViewHolder).binding

        binding.profileNickname.text = dataSet[position]

        binding.postSetting.setOnClickListener {

        }
    }

    override fun getItemCount(): Int {
        Log.d("RecyclerView", "init data Size: ${dataSet.size}")
        return dataSet.size
    }
}