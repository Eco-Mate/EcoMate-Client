package com.example.ecomate.ui.challenge

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.ecomate.R
import com.example.ecomate.databinding.ActivityChallengeBinding
import com.example.ecomate.ui.adapter.ChallengeAdapter
import com.example.ecomate.viewmodel.ChallengeViewModel

class ChallengeActivity : AppCompatActivity() {

    lateinit var binding: ActivityChallengeBinding
    private val challengeViewModel: ChallengeViewModel by viewModels()
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
                override fun onClick(myChallengeId: Int) {
                    val intent = Intent(this@ChallengeActivity, ChallengeDetailActivity::class.java)
                    intent.putExtra("mode", 2)
                    intent.putExtra("myChallengeId", myChallengeId)
                    startActivity(intent)
                }
            }
        challengeAdapter.reChallengeListener =
            object : ChallengeAdapter.ReChallengeListener {
                override fun onClick(challengeId: Int) {
                    challengeViewModel.tryChallenge(challengeId)
                }
            }

        binding.challengeRv.adapter = challengeAdapter

        challengeViewModel.finishMyChallenge.observe(this) {
            challengeAdapter.submitList(it)
        }
    }

    private fun setUi() {
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }
        binding.toolbar.title = resources.getString(R.string.all_challenge)
    }
}