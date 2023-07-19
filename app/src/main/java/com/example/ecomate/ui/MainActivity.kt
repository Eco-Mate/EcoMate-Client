package com.example.ecomate.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.ecomate.R
import com.example.ecomate.databinding.ActivityMainBinding
import com.example.ecomate.ui.chat.ChatFragment
import com.example.ecomate.ui.community.CommunityFragment
import com.example.ecomate.ui.map.MapFragment
import com.example.ecomate.ui.myprofile.MyProfileFragment
import com.example.ecomate.ui.home.HomeFragment
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val tabLayout = binding.tabs

        supportFragmentManager.beginTransaction().add(R.id.tab_content, HomeFragment()).commit()

        tabLayout.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val transaction = supportFragmentManager.beginTransaction()
                when(tab?.text) {
                    "홈" -> transaction.replace(
                        R.id.tab_content,
                        HomeFragment()
                    )
                    "커뮤니티" -> {
                        // 나중에 Back-end에서 데이터 받아올 부분
                        val dataSet = mutableListOf<String>()
                        dataSet.add("지구지킴이")

                        transaction.replace(R.id.tab_content, CommunityFragment(dataSet))
                    }
                    "에코챗" -> transaction.replace(R.id.tab_content, ChatFragment())
                    "에코맵" -> transaction.replace(R.id.tab_content, MapFragment())
                    "내정보" -> transaction.replace(R.id.tab_content, MyProfileFragment())
                }
                transaction.commit()
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