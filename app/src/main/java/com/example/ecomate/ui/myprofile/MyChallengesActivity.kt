package com.example.ecomate.ui.myprofile

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.ecomate.ApplicationClass
import com.example.ecomate.ApplicationClass.Companion.USER_INFO
import com.example.ecomate.R
import com.example.ecomate.databinding.ActivityFollowInfoBinding
import com.example.ecomate.databinding.ActivityMyChallengesBinding
import com.example.ecomate.databinding.ActivityUserProfileBinding
import com.example.ecomate.model.Board
import com.example.ecomate.model.ProfileInfo
import com.example.ecomate.model.User
import com.example.ecomate.ui.adapter.FollowInfoAdapter
import com.example.ecomate.ui.adapter.MyChallengesAdapter
import com.example.ecomate.ui.adapter.UserBoardsAdapter
import com.example.ecomate.ui.community.BoardDetailActivity
import com.example.ecomate.viewmodel.FollowInfoViewModel
import com.example.ecomate.viewmodel.MyChallengesViewModel
import com.example.ecomate.viewmodel.MyProfileViewModel
import com.example.ecomate.viewmodel.UserProfileViewModel
import com.google.android.material.tabs.TabLayout
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MyChallengesActivity : AppCompatActivity() {
    lateinit var binding: ActivityMyChallengesBinding
    private val myChallengesViewModel: MyChallengesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyChallengesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setAdapter()
        setUi()
    }
    private fun setAdapter() {
        val myChallengesAdapter = MyChallengesAdapter()
        myChallengesAdapter.detailMyChallengeListener =
            object : MyChallengesAdapter.DetailMyChallengeListener {
                override fun onClick(challengeId: Int) {

                }
            }
        binding.challengesRv.apply {
            layoutManager = LinearLayoutManager(this.context)
            adapter = myChallengesAdapter
        }

        myChallengesViewModel.myChallenges.observe(this@MyChallengesActivity) {
            myChallengesAdapter.submitList(it)
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