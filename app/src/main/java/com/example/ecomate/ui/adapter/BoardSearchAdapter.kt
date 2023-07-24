package com.example.ecomate.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ecomate.databinding.ItemBoardSearchBinding
import com.example.ecomate.model.Board

class BoardSearchViewHolder(val binding: ItemBoardSearchBinding): RecyclerView.ViewHolder(binding.root)

class BoardSearchAdapter(val dataSet: List<Board>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private lateinit var binding: ItemBoardSearchBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        binding = ItemBoardSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BoardSearchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        binding.apply {
            Glide.with(holder.itemView)
                .load(dataSet[position].image)
                .into(boardImage)
            root.setOnClickListener {
                detailBoardListener.onClick(board = dataSet[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    interface DetailBoardListener {
        fun onClick(board: Board)
    }

    lateinit var detailBoardListener: DetailBoardListener
}