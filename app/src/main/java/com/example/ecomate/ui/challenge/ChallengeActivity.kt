package com.example.ecomate.ui.challenge

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.ecomate.databinding.ActivityChallengeBinding
import com.example.ecomate.ui.adapter.ChallengeAdapter
import com.example.ecomate.viewmodel.HomeViewModel

class ChallengeActivity : AppCompatActivity() {

    lateinit var binding: ActivityChallengeBinding
    private val homeViewModel: HomeViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChallengeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setAdapter()
        setUi()

    }

    private fun setAdapter() {
        val challengeAdapter = ChallengeAdapter()
        challengeAdapter.detailChallengeListener =
            object : ChallengeAdapter.DetailChallengeListener {
                override fun onClick(challengeId: Int) {
                    val intent = Intent(
                        this@ChallengeActivity,
                        ChallengeDetailActivity::class.java
                    )
                    intent.putExtra("challengeId", challengeId)
                    startActivity(intent)
                }
            }

        binding.challengeRv.adapter = challengeAdapter

        homeViewModel.challengeList.observe(this) {
            challengeAdapter.submitList(it)
        }
    }

    private fun setUi() {
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }
        binding.toolbar.title = "전체 챌린지"


    }
}