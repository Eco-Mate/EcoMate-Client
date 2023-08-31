package com.example.ecomate.ui.myprofile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ecomate.ApplicationClass.Companion.USER_INFO
import com.example.ecomate.databinding.ActivityFollowInfoBinding
import com.example.ecomate.ui.adapter.FollowInfoAdapter
import com.example.ecomate.viewmodel.FollowInfoViewModel
import com.google.android.material.tabs.TabLayout

class FollowInfoActivity : AppCompatActivity() {
    lateinit var binding: ActivityFollowInfoBinding
    private val followInfoViewModel: FollowInfoViewModel by viewModels()
    private val followInfoAdapter = FollowInfoAdapter()
    lateinit var nickname: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFollowInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        nickname = intent.getStringExtra("userNickname").toString()
        followInfoViewModel.getFollowerUsers(nickname)
        followInfoViewModel.getFollowingUsers(nickname)

        setAdapter()
        setUi()
    }

    override fun onResume() {
        super.onResume()
        followInfoViewModel.getFollowerUsers(nickname)
        followInfoViewModel.getFollowingUsers(nickname)
    }

    private fun setAdapter() {
        followInfoAdapter.detailUserProfileListener =
            object : FollowInfoAdapter.DetailUserProfileListener {
                override fun onClick(memberId: Int) {
                    followInfoViewModel.getUserProfile(memberId)
                    followInfoViewModel.profileInfo.observe(this@FollowInfoActivity) {
                        val intent = Intent(this@FollowInfoActivity, UserProfileActivity::class.java)
                        intent.putExtra(USER_INFO, it)
                        startActivity(intent)
                    }
                }
            }
        binding.userRv.apply {
            layoutManager = LinearLayoutManager(this.context)
            adapter = followInfoAdapter
        }

        followInfoViewModel.followerUsers.observe(this@FollowInfoActivity) {
            followInfoAdapter.submitList(it)
        }
    }
    private fun setUi() {
        binding.apply {
            backBtn.setOnClickListener {
                finish()
            }
            userNickname.text = nickname
            searchBtn.setOnClickListener {

            }
            tabs.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener{
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    when(tab?.text) {
                        "팔로워" -> {
                            followInfoViewModel.followerUsers.observe(this@FollowInfoActivity) {
                                followInfoAdapter.submitList(it)
                            }
                        }
                        "팔로잉" -> {
                            followInfoViewModel.followingUsers.observe(this@FollowInfoActivity) {
                                followInfoAdapter.submitList(it)
                            }
                        }
                    }
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {
                    Log.d("TabButton", "onTabUnselected...")
                }

                override fun onTabReselected(tab: TabLayout.Tab?) {
                    Log.d("TabButton", "onTabReselected...")
                }
            })
        }
    }
}