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
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUi()
    }

    private fun setUi() {
        supportFragmentManager.beginTransaction().add(R.id.tab_content, HomeFragment()).commit()
        binding.apply {
            tabs.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener{
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    val transaction = supportFragmentManager.beginTransaction()
                    when(tab?.text) {
                        "홈" -> transaction.replace(R.id.tab_content, HomeFragment())
                        "커뮤니티" -> transaction.replace(R.id.tab_content, CommunityFragment())
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
}