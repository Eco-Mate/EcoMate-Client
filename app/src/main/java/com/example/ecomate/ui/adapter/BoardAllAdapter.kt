package com.example.ecomate.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ecomate.databinding.ItemBoardBinding
import com.example.ecomate.model.Board

class BoardViewHolder(val binding: ItemBoardBinding): RecyclerView.ViewHolder(binding.root)

class BoardAllAdapter(val dataSet: List<Board>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private lateinit var binding: ItemBoardBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        binding = ItemBoardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BoardViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        binding.apply {
            profileNickname.text = dataSet[position].nickname
            boardDate.text = dataSet[position].createdDate.substring(0,4) +
                    "." + dataSet[position].createdDate.substring(5,7) +
                    "." + dataSet[position].createdDate.substring(8,10)
            Glide.with(holder.itemView)
                .load(dataSet[position].image)
                .into(boardImg)
            boardContent.text = dataSet[position].boardContent
            root.setOnClickListener {
                detailBoardListener.onClick(boardId = dataSet[position].boardId, board = dataSet[position])
            }
        }

    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    interface DetailBoardListener {
        fun onClick(boardId: Int, board: Board)
    }

    lateinit var detailBoardListener: DetailBoardListener
}