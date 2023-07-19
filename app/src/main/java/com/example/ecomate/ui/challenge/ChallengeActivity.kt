package com.example.ecomate.ui.challenge

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.ecomate.databinding.ActivityChallengeBinding
import com.example.ecomate.viewmodel.HomeViewModel

class ChallengeActivity : AppCompatActivity() {

    lateinit var binding: ActivityChallengeBinding
    private val homeViewModel: HomeViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChallengeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUi()
    }

    private fun setUi() {
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }
    }
}