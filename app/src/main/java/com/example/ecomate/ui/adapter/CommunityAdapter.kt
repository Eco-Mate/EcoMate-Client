package com.example.ecomate.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ecomate.databinding.ItemPostBinding

class CommunityViewHolder(val binding: ItemPostBinding): RecyclerView.ViewHolder(binding.root)

class CommunityAdapter(val dataSet: MutableList<String>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CommunityViewHolder(ItemPostBinding.inflate(LayoutInflater.from(parent.context),parent,false))
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