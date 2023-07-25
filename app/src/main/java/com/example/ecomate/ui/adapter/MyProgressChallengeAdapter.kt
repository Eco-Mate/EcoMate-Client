package com.example.ecomate.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.ecomate.databinding.ItemChallengeProgressBinding
import com.example.ecomate.model.MyChallenge


class MyProgressChallengeAllAdapter :
    ListAdapter<MyChallenge, MyProgressChallengeAllAdapter.MyProgressChallengeViewHolder>(
        MyProgressChallengeDiffCallback()
    ) {
    private lateinit var binding: ItemChallengeProgressBinding

    inner class MyProgressChallengeViewHolder(private val binding: ItemChallengeProgressBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(myChallenge: MyChallenge) {
            binding.apply {

            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyProgressChallengeViewHolder {
        binding =
            ItemChallengeProgressBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyProgressChallengeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyProgressChallengeViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    interface DetailMyProgressChallengeListener {
        fun onClick(challengeId: Int)

    }

    lateinit var detailMyProgressChallengeListener: DetailMyProgressChallengeListener
}

class MyProgressChallengeDiffCallback : DiffUtil.ItemCallback<MyChallenge>() {
    override fun areItemsTheSame(oldItem: MyChallenge, newItem: MyChallenge): Boolean {
        return oldItem.myChallengeId == newItem.myChallengeId
    }

    override fun areContentsTheSame(oldItem: MyChallenge, newItem: MyChallenge): Boolean {
        return newItem == oldItem
    }
}