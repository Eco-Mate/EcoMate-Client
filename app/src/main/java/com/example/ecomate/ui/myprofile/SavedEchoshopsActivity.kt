package com.example.ecomate.ui.myprofile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ecomate.databinding.ActivitySaveEchoshopsBinding

class SavedEchoshopsActivity : AppCompatActivity() {
    lateinit var binding: ActivitySaveEchoshopsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySaveEchoshopsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setAdapter()
        setUi()
    }

    private fun setAdapter() {

    }

    private fun setUi() {
        binding.apply {
            backBtn.setOnClickListener {
                finish()
            }

        }
    }
}