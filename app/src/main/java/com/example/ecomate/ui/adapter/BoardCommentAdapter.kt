package com.example.ecomate.ui.adapter

import android.icu.text.SimpleDateFormat
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ecomate.databinding.ItemCommentBinding
import com.example.ecomate.model.Comment

class BoardCommentViewHolder(val binding: ItemCommentBinding): RecyclerView.ViewHolder(binding.root)

class BoardCommentAdapter(val dataSet: List<Comment>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private lateinit var binding: ItemCommentBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        binding = ItemCommentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BoardCommentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        binding.apply {
            profileNickname.text = dataSet[position].nickname
            commentDate.text = SimpleDateFormat("yyyy.MM.dd").format(dataSet[position].createdDate)
            commentContent.text = dataSet[position].commentContent
        }
    }

    override fun getItemCount(): Int {
        Log.d("RecyclerView", "init data size: ${dataSet.size}")
        return dataSet.size
    }
}