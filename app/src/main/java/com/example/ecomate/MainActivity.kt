package com.example.ecomate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.ecomate.databinding.ActivityMainBinding
import com.example.ecomate.fragment.ChatFragment
import com.example.ecomate.fragment.CommunityFragment
import com.example.ecomate.fragment.HomeFragment
import com.example.ecomate.fragment.MapFragment
import com.example.ecomate.fragment.ProfileFragment
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
                    "홈" -> transaction.replace(R.id.tab_content, HomeFragment())
                    "커뮤니티" -> {
                        // 나중에 Back-end에서 데이터 받아올 부분
                        val dataSet = mutableListOf<String>()
                        dataSet.add("지구지킴이")

                        transaction.replace(R.id.tab_content, CommunityFragment(dataSet))
                    }
                    "에코챗" -> transaction.replace(R.id.tab_content, ChatFragment())
                    "에코맵" -> transaction.replace(R.id.tab_content, MapFragment())
                    "내정보" -> transaction.replace(R.id.tab_content, ProfileFragment())
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