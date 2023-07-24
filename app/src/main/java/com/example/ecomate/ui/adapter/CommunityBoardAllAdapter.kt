package com.example.ecomate.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.ecomate.databinding.ItemPostBinding
import com.example.ecomate.model.Board

class CommunityBoardViewHolder(val binding: ItemPostBinding): RecyclerView.ViewHolder(binding.root)

class CommunityBoardAllAdapter(val dataSet: List<Board>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private lateinit var binding: ItemPostBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        binding = ItemPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CommunityBoardViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        Log.d("RecyclerView", "onBindViewHolder(): $position")
        binding.postContent.text = dataSet[position].boardContent

        binding.root.setOnClickListener {
            detailBoardListener.onClick(boardId = dataSet[position].boardId)
        }
    }

    override fun getItemCount(): Int {
        Log.d("RecyclerView", "init data size: ${dataSet.size}")
        return dataSet.size
    }

    interface DetailBoardListener {
        fun onClick(boardId: Int)
    }

    lateinit var detailBoardListener: DetailBoardListener
}