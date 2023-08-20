package com.example.ecomate.ui.myprofile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ecomate.databinding.ActivityEditProfileBinding

class EditProfileActivity : AppCompatActivity() {
    lateinit var binding: ActivityEditProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUi()
    }

    private fun setUi() {
        binding.apply {
            backBtn.setOnClickListener {
                finish()
            }
            editImgBtn.setOnClickListener {

            }
            editNicknameBtn.setOnClickListener {

            }
            editPasswordBtn.setOnClickListener {

            }
            editEmailBtn.setOnClickListener {

            }
            editStateBtn.setOnClickListener {

            }
        }
    }
}