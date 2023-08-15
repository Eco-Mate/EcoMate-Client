package com.example.ecomate.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ecomate.R
import com.example.ecomate.databinding.ItemBoardSearchBinding
import com.example.ecomate.databinding.ItemChallengeAllBinding
import com.example.ecomate.model.Board
import com.example.ecomate.model.Challenge

class BoardSearchAdapter :
    ListAdapter<Board, BoardSearchAdapter.BoardSearchViewHolder>(
        BoardSearchDiffCallback()
    ) {
    private lateinit var binding: ItemBoardSearchBinding

    inner class BoardSearchViewHolder(private val binding: ItemBoardSearchBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setBind(board: Board) {
            binding.apply {
                Glide.with(this.root)
                    .load(board.image)
                    .into(boardImage)
                root.setOnClickListener {
                    detailBoardListener.onClick(
                        board = board
                    )
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BoardSearchViewHolder {
        binding = ItemBoardSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BoardSearchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BoardSearchViewHolder, position: Int) {
        holder.setBind(getItem(position))
    }

    interface DetailBoardListener {
        fun onClick(board: Board)
    }

    lateinit var detailBoardListener: DetailBoardListener
}

class BoardSearchDiffCallback : DiffUtil.ItemCallback<Board>() {
    override fun areItemsTheSame(oldItem: Board, newItem: Board): Boolean {
        return oldItem.boardId == newItem.boardId
    }

    override fun areContentsTheSame(oldItem: Board, newItem: Board): Boolean {
        return oldItem == newItem
    }
}