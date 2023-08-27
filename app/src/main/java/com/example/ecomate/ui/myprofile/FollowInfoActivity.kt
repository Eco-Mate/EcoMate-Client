package com.example.ecomate.ui.myprofile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ecomate.databinding.ActivityFollowInfoBinding
import com.example.ecomate.model.User
import com.example.ecomate.ui.adapter.FollowInfoAdapter
import com.example.ecomate.viewmodel.FollowInfoViewModel
import com.example.ecomate.viewmodel.MyProfileViewModel
import com.google.android.material.tabs.TabLayout

class FollowInfoActivity : AppCompatActivity() {
    lateinit var binding: ActivityFollowInfoBinding
    private val followInfoAdapter = FollowInfoAdapter()
    lateinit var nickname: String
    private val followInfoViewModel: FollowInfoViewModel by viewModels()

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
    private fun setAdapter() {
        followInfoAdapter.detailFollowInfoListener =
            object : FollowInfoAdapter.DetailFollowInfoListener {
                override fun onClick(userId: Int) {
                    Toast.makeText(this@FollowInfoActivity, "$userId Clicked!", Toast.LENGTH_SHORT).show()
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