package com.example.ecomate.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.ecomate.R
import com.example.ecomate.databinding.ItemChallengeAllBinding
import com.example.ecomate.databinding.ItemPopularBoardBinding
import com.example.ecomate.model.Board
import com.example.ecomate.model.Challenge
import com.example.ecomate.ui.util.Util.loadImg

class HomePopularBoardsAdapter : ListAdapter<Board, HomePopularBoardsAdapter.HomeBoardsViewHolder>(
        HomeBoardsDiffCallback()
    ) {
    private lateinit var binding: ItemPopularBoardBinding

    inner class HomeBoardsViewHolder(private val binding: ItemPopularBoardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(board: Board) {
            binding.apply {
                boardTitle.text = board.boardTitle
                root.setOnClickListener {
                    detailHomeBoardListener.onClick(board = board)
                }
                loadImg(binding.root.context, board.image, binding.boardImage)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeBoardsViewHolder {
        binding =
            ItemPopularBoardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeBoardsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeBoardsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    interface DetailHomeBoardListener {
        fun onClick(board: Board)

    }

    lateinit var detailHomeBoardListener: DetailHomeBoardListener
}

class HomeBoardsDiffCallback : DiffUtil.ItemCallback<Board>() {
    override fun areItemsTheSame(oldItem: Board, newItem: Board): Boolean {
        return oldItem.boardId == newItem.boardId
    }

    override fun areContentsTheSame(oldItem: Board, newItem: Board): Boolean {
        return newItem == oldItem
    }
}