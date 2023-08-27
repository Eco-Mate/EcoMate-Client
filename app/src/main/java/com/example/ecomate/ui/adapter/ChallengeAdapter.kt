package com.example.ecomate.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.ecomate.databinding.ItemChallengeBinding
import com.example.ecomate.model.MyDetailChallenge
import com.example.ecomate.ui.util.Util.loadImg

class ChallengeAdapter :
    ListAdapter<MyDetailChallenge, ChallengeAdapter.ChallengeViewHolder>(ChallengeDiffCallback()) {
    lateinit var binding: ItemChallengeBinding

    inner class ChallengeViewHolder(private val binding: ItemChallengeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(myChallenge: MyDetailChallenge) {
            binding.apply {
                challengeDate.text = myChallenge.createdDate
                challengeTitle.text = myChallenge.challengeTitle
                root.setOnClickListener {
                    detailChallengeListener.onClick(myChallenge.myChallengeId)
                }
                loadImg(binding.root.context, myChallenge.image, binding.challengeIv)
                reChallengeBtn.setOnClickListener {
                    reChallengeListener.onClick(myChallenge.challengeId)
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
        fun onClick(myChallengeId: Int)
    }

    lateinit var reChallengeListener: ReChallengeListener

    interface ReChallengeListener {
        fun onClick(challengeId: Int)
    }
}

class ChallengeDiffCallback : DiffUtil.ItemCallback<MyDetailChallenge>() {
    override fun areItemsTheSame(oldItem: MyDetailChallenge, newItem: MyDetailChallenge): Boolean {
        return oldItem.myChallengeId == newItem.myChallengeId
    }

    override fun areContentsTheSame(
        oldItem: MyDetailChallenge,
        newItem: MyDetailChallenge
    ): Boolean {
        return newItem == oldItem
    }
}