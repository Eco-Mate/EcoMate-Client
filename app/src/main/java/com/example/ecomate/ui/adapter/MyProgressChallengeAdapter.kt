package com.example.ecomate.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.ecomate.databinding.ItemChallengeProgressBinding
import com.example.ecomate.model.MyDetailChallenge
import com.example.ecomate.ui.util.Util.loadImg


class MyProgressChallengeAllAdapter :
    ListAdapter<MyDetailChallenge, MyProgressChallengeAllAdapter.MyProgressChallengeViewHolder>(
        MyProgressChallengeDiffCallback()
    ) {
    private lateinit var binding: ItemChallengeProgressBinding

    inner class MyProgressChallengeViewHolder(private val binding: ItemChallengeProgressBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(myChallenge: MyDetailChallenge) {
            binding.apply {
                loadImg(binding.root.context, myChallenge.image, binding.challengeProgressIv)
                challengeProgressTitle.text = myChallenge.challengeTitle
                root.setOnClickListener {
                    detailMyProgressChallengeListener.onClick(myChallenge.myChallengeId)
                }

                challengeProgressBar.max = myChallenge.goalCnt
                challengeProgressBar.progress = myChallenge.doneCnt

                val temp = (myChallenge.doneCnt.toFloat() / myChallenge.goalCnt.toFloat()) * 100

                challengeProgressDes.text =
                    "${temp.toInt()}% 달성 (${myChallenge.doneCnt}회/${myChallenge.goalCnt}회)"
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): MyProgressChallengeViewHolder {
        binding =
            ItemChallengeProgressBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyProgressChallengeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyProgressChallengeViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    interface DetailMyProgressChallengeListener {
        fun onClick(myChallengeId: Int)

    }

    lateinit var detailMyProgressChallengeListener: DetailMyProgressChallengeListener
}

class MyProgressChallengeDiffCallback : DiffUtil.ItemCallback<MyDetailChallenge>() {
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