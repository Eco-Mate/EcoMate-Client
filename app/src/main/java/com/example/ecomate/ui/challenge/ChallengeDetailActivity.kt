package com.example.ecomate.ui.challenge

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.ecomate.databinding.ActivityChallengeDetailBinding
import com.example.ecomate.viewmodel.DetailChallengeViewModel

class ChallengeDetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityChallengeDetailBinding
    private val detailChallengeViewModel: DetailChallengeViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChallengeDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        detailChallengeViewModel.getDetailChallenge(intent.getIntExtra("challengeId", 0))

        setUI()
    }

    private fun setUI() {
        detailChallengeViewModel.challengeDetail.observe(this) {
            binding.apply {
                toolbar.title = it.challengeTitle
                challengeContent.text = it.description
            }
        }
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }
    }
}