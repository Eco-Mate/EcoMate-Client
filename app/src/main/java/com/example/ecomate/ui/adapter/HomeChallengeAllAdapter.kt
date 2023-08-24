package com.example.ecomate.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.ecomate.R
import com.example.ecomate.databinding.ItemChallengeAllBinding
import com.example.ecomate.model.Challenge
import com.example.ecomate.ui.util.Util.loadImg

class HomeChallengeAllAdapter :
    ListAdapter<Challenge, HomeChallengeAllAdapter.HomeChallengeViewHolder>(
        HomeChallengeDiffCallback()
    ) {
    private lateinit var binding: ItemChallengeAllBinding

    inner class HomeChallengeViewHolder(private val binding: ItemChallengeAllBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(challenge: Challenge) {
            binding.apply {
                challengeAllTitle.text = challenge.challengeTitle
                challengeAllPeople.text = String.format(
                    root.context.resources.getString(R.string.trying),
                    challenge.goalCnt
                )
                root.setOnClickListener {
                    detailHomeChallengeListener.onClick(challengeId = challenge.challengeId)
                }
                loadImg(binding.root.context, challenge.image, binding.challengeAllIv)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeChallengeViewHolder {
        binding =
            ItemChallengeAllBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeChallengeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeChallengeViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    interface DetailHomeChallengeListener {
        fun onClick(challengeId: Int)

    }

    lateinit var detailHomeChallengeListener: DetailHomeChallengeListener
}

class HomeChallengeDiffCallback : DiffUtil.ItemCallback<Challenge>() {
    override fun areItemsTheSame(oldItem: Challenge, newItem: Challenge): Boolean {
        return oldItem.challengeId == newItem.challengeId
    }

    override fun areContentsTheSame(oldItem: Challenge, newItem: Challenge): Boolean {
        return newItem == oldItem
    }
}