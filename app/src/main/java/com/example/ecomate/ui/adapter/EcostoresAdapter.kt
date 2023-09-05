package com.example.ecomate.ui.adapter

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ecomate.ApplicationClass
import com.example.ecomate.R
import com.example.ecomate.databinding.ItemStoreBinding
import com.example.ecomate.model.Board
import com.example.ecomate.model.StoreInfo

class EcostoresAdapter : ListAdapter<StoreInfo, EcostoresAdapter.EcostoresViewHolder>(
    StoreAllDiffCallback()
) {
    private lateinit var binding: ItemStoreBinding

    inner class EcostoresViewHolder(private val binding: ItemStoreBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @RequiresApi(Build.VERSION_CODES.Q)
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
                storeMore.setOnClickListener {
                    setPopUpMenu(this.root.context, it, storeInfo)
                }
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

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onBindViewHolder(holder: EcostoresViewHolder, position: Int) {
        holder.setBind(getItem(position))
    }

    interface DetailStoreListener {
        fun onClick(storeInfo: StoreInfo)
    }
    interface LikeStoreListener {
        fun onClick(storeInfo: StoreInfo)
    }
    interface UnlikeStoreListener {
        fun onClick(storeInfo: StoreInfo)
    }

    lateinit var detailStoreListener: DetailStoreListener
    lateinit var likeStoreListener: LikeStoreListener
    lateinit var unlikeStoreListener: UnlikeStoreListener

    @RequiresApi(Build.VERSION_CODES.Q)
    private fun setPopUpMenu(context: Context, view: View, storeInfo: StoreInfo) {
        val popUp = PopupMenu(context, view)
        popUp.menuInflater.inflate(R.menu.store_menu, popUp.menu)
        popUp.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.like -> likeStoreListener.onClick(storeInfo = storeInfo)
                R.id.unlike -> unlikeStoreListener.onClick(storeInfo = storeInfo)
            }
            false
        }
        popUp.show()
    }
}

class StoreAllDiffCallback : DiffUtil.ItemCallback<StoreInfo>() {
    override fun areItemsTheSame(oldItem: StoreInfo, newItem: StoreInfo): Boolean {
        return oldItem.storeId == newItem.storeId
    }

    override fun areContentsTheSame(oldItem: StoreInfo, newItem: StoreInfo): Boolean {
        return oldItem == newItem
    }
}