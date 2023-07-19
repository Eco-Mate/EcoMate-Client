package com.example.ecomate.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.ecomate.databinding.ItemChallengeBinding
import com.example.ecomate.model.Challenge

class ChallengeAdapter :
    ListAdapter<Challenge, ChallengeAdapter.ChallengeViewHolder>(ChallengeDiffCallback()) {
    lateinit var binding: ItemChallengeBinding

    inner class ChallengeViewHolder(private val binding: ItemChallengeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(challenge: Challenge) {
            binding.apply {
                challengeDate.text = challenge.createdDate
                challengeTitle.text = challenge.challengeTitle
                root.setOnClickListener {
                    detailChallengeListener.onClick(challengeId = challenge.challengeId)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChallengeViewHolder {
        binding =
            ItemChallengeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ChallengeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ChallengeViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    lateinit var detailChallengeListener: DetailChallengeListener

    interface DetailChallengeListener {
        fun onClick(challengeId: Int)

    }
}

class ChallengeDiffCallback : DiffUtil.ItemCallback<Challenge>() {
    override fun areItemsTheSame(oldItem: Challenge, newItem: Challenge): Boolean {
        return oldItem.challengeId == newItem.challengeId
    }

    override fun areContentsTheSame(oldItem: Challenge, newItem: Challenge): Boolean {
        return newItem == oldItem
    }
}