package com.example.ecomate.ui.myprofile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ecomate.databinding.ActivityFollowInfoBinding
import com.example.ecomate.model.User
import com.example.ecomate.ui.adapter.FollowInfoAdapter
import com.google.android.material.tabs.TabLayout

class FollowInfoActivity : AppCompatActivity() {
    lateinit var binding: ActivityFollowInfoBinding
    var followerList: List<User> = mutableListOf(
        User(0,"follower_user_1",""),
        User(1,"follower_user_2",""),
        User(2,"follower_user_3","")
    )
    var followingList: List<User> = mutableListOf(
        User(0,"following_user_1",""),
        User(1,"following_user_2",""),
        User(2,"following_user_3","")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFollowInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setAdapter()
        setUi()
    }
    private fun setAdapter() {
        binding.userRv.apply {
            layoutManager = LinearLayoutManager(this.context)
            adapter = FollowInfoAdapter(followerList)
        }
    }
    private fun setUi() {
        binding.apply {
            backBtn.setOnClickListener {
                finish()
            }
            userNickname.text = intent.getStringExtra("userNickname")
            searchBtn.setOnClickListener {

            }
            tabs.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener{
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    when(tab?.text) {
                        "팔로워" -> binding.userRv.adapter = FollowInfoAdapter(followerList)
                        "팔로잉" -> binding.userRv.adapter = FollowInfoAdapter(followingList)
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