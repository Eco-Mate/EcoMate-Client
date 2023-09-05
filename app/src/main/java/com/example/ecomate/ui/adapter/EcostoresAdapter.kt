package com.example.ecomate.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ecomate.R
import com.example.ecomate.databinding.ItemStoreBinding
import com.example.ecomate.model.StoreInfo

class EcostoresAdapter : ListAdapter<StoreInfo, EcostoresAdapter.EcostoresViewHolder>(
    StoreAllDiffCallback()
) {
    private lateinit var binding: ItemStoreBinding

    inner class EcostoresViewHolder(private val binding: ItemStoreBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setBind(storeInfo: StoreInfo) {
            binding.apply {
                if (storeInfo.image != null && storeInfo.image != "") {
                    Glide.with(this.root.context)
                        .load(storeInfo.image)
                        .into(ecostoreImg)
                } else {
                    ecostoreImg.setImageResource(R.drawable.standard_store_icon)
                }
                ecostoreName.text = storeInfo.storeName
                ecostoreAddress.text = storeInfo.address
                root.setOnClickListener {
                    detailStoreListener.onClick(storeInfo = storeInfo)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EcostoresViewHolder {
        binding = ItemStoreBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EcostoresViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EcostoresViewHolder, position: Int) {
        holder.setBind(getItem(position))
    }

    interface DetailStoreListener {
        fun onClick(storeInfo: StoreInfo)
    }

    lateinit var detailStoreListener: DetailStoreListener
}

class StoreAllDiffCallback : DiffUtil.ItemCallback<StoreInfo>() {
    override fun areItemsTheSame(oldItem: StoreInfo, newItem: StoreInfo): Boolean {
        return oldItem.storeId == newItem.storeId
    }

    override fun areContentsTheSame(oldItem: StoreInfo, newItem: StoreInfo): Boolean {
        return oldItem == newItem
    }
}