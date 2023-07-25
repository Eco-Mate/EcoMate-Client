package com.example.ecomate.ui.myprofile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ecomate.databinding.ActivityFollowInfoBinding
import com.example.ecomate.databinding.ActivityLoginBinding
import com.example.ecomate.ui.MainActivity

class FollowInfoActivity : AppCompatActivity() {
    lateinit var binding: ActivityFollowInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFollowInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUi()
    }

    private fun setUi() {
        binding.apply {
            backBtn.setOnClickListener {
                finish()
            }
            userNickname.text = "지구지킴이"

        }
    }
}