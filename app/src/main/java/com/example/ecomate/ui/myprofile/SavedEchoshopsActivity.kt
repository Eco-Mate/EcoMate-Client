package com.example.ecomate.ui.myprofile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ecomate.databinding.ActivitySaveEchoshopsBinding
import com.example.ecomate.model.StoreInfo
import com.example.ecomate.ui.adapter.EcostoresAdapter
import com.example.ecomate.viewmodel.SavedBoardsViewModel
import com.example.ecomate.viewmodel.SavedEcostoresViewModel

class SavedEchoshopsActivity : AppCompatActivity() {
    lateinit var binding: ActivitySaveEchoshopsBinding
    private val savedEcostoresViewModel: SavedEcostoresViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySaveEchoshopsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setAdapter()
        setUi()
    }

    private fun setAdapter() {
        val ecostoresAdapter = EcostoresAdapter()
        ecostoresAdapter.detailStoreListener =
            object : EcostoresAdapter.DetailStoreListener {
                override fun onClick(storeInfo: StoreInfo) {

                }
            }
        ecostoresAdapter.likeStoreListener =
            object : EcostoresAdapter.LikeStoreListener {
                override fun onClick(storeInfo: StoreInfo) {
                    savedEcostoresViewModel.apply {
                        if (storeInfo.liked) {
                            Toast.makeText(binding.root.context, "해당 매장을 이미 좋아요 했습니다.", Toast.LENGTH_SHORT).show()
                        } else {
                            postLikeEcoStore(storeInfo.storeId)
                            Toast.makeText(binding.root.context, "매장을 좋아요 했습니다.", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        ecostoresAdapter.unlikeStoreListener =
            object : EcostoresAdapter.UnlikeStoreListener {
                override fun onClick(storeInfo: StoreInfo) {
                    savedEcostoresViewModel.apply {
                        if (storeInfo.liked) {
                            postUnlikeEcoStore(storeInfo.storeId)
                            Toast.makeText(binding.root.context, "해당 매장의 좋아요를 취소했습니다.", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(binding.root.context, "해당 매장을 이미 좋아요 취소했습니다.", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }

        binding.saveEchoshopRv.apply {
            layoutManager = LinearLayoutManager(this.context)
            adapter = ecostoresAdapter
            addItemDecoration(
                DividerItemDecoration(
                    this.context,
                    LinearLayoutManager.VERTICAL
                )
            )
        }

        savedEcostoresViewModel.ecostores.observe(this) {
            ecostoresAdapter.submitList(it)
        }
    }

    private fun setUi() {
        binding.apply {
            backBtn.setOnClickListener {
                finish()
            }

        }
    }
}