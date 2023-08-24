package com.example.ecomate.ui.challenge

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.ecomate.databinding.ActivityEditChallengeBinding
import com.example.ecomate.model.Challenge
import com.example.ecomate.viewmodel.EditChallengeViewModel

class EditChallengeActivity : AppCompatActivity() {
    lateinit var binding: ActivityEditChallengeBinding

    private val editChallengeViewModel: EditChallengeViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditChallengeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            postChallengeBtn.setOnClickListener {
//                editChallengeViewModel.postChallenge(
//                    Challenge(
//                        true, -1,
//                        editTitle.text.toString(), "", editContent.text.toString(), 3, 30
//                    )
//                )
                finish()
            }
            toolbar.setNavigationOnClickListener {
                finish()
            }
        }


    }
}