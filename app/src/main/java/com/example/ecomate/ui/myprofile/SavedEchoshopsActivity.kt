package com.example.ecomate.ui.myprofile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
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