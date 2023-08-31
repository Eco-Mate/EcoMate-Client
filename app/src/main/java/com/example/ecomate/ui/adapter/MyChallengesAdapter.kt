package com.example.ecomate.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ecomate.R
import com.example.ecomate.databinding.ItemChallengeAllBinding
import com.example.ecomate.databinding.ItemMyChallengeBinding
import com.example.ecomate.model.Challenge
import com.example.ecomate.model.MyDetailChallenge
import com.example.ecomate.ui.util.Util.loadImg

class MyChallengesAdapter :
    ListAdapter<MyDetailChallenge, MyChallengesAdapter.MyChallengesViewHolder>(
        MyChallengesDiffCallback()
    ) {
    private lateinit var binding: ItemMyChallengeBinding

    inner class MyChallengesViewHolder(private val binding: ItemMyChallengeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(myDetailChallenge: MyDetailChallenge) {
            binding.apply {
                if (myDetailChallenge.image != null && myDetailChallenge.image != "") {
                    Glide.with(this.root.context)
                        .load(myDetailChallenge.image)
                        .into(challengeImage)
                }
                challengeTitle.text = myDetailChallenge.challengeTitle
                challengeProgressBar.progress = ((myDetailChallenge.doneCnt/myDetailChallenge.goalCnt.toFloat())*100).toInt()
                challengeProgressDes.text = "${((myDetailChallenge.doneCnt/myDetailChallenge.goalCnt.toFloat())*100).toInt()}% 달성 " +
                        "(${myDetailChallenge.doneCnt}회/${myDetailChallenge.goalCnt}회)"
                root.setOnClickListener {
                    detailMyChallengeListener.onClick(challengeId = myDetailChallenge.challengeId)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyChallengesViewHolder {
        binding =
            ItemMyChallengeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyChallengesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyChallengesViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    interface DetailMyChallengeListener {
        fun onClick(challengeId: Int)

    }

    lateinit var detailMyChallengeListener: DetailMyChallengeListener
}

class MyChallengesDiffCallback : DiffUtil.ItemCallback<MyDetailChallenge>() {
    override fun areItemsTheSame(oldItem: MyDetailChallenge, newItem: MyDetailChallenge): Boolean {
        return oldItem.challengeId == newItem.challengeId
    }

    override fun areContentsTheSame(oldItem: MyDetailChallenge, newItem: MyDetailChallenge): Boolean {
        return newItem == oldItem
    }
}