package com.example.ecomate.ui.community

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.ecomate.databinding.ActivityBoardAddBinding
import com.example.ecomate.model.Board
import java.text.SimpleDateFormat

class BoardAddActivity : AppCompatActivity() {
    lateinit var binding: ActivityBoardAddBinding
    lateinit var item: Board
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBoardAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUi()
    }

    private fun setUi() {
        binding.apply {
            backBtn.setOnClickListener {
                finish()
            }
            boardImageBtn.setOnClickListener {

            }
            boardAddBtn.setOnClickListener {
//                item = Board(
//                    0,
//                    "HJTN",
//                    boardChallengeEditText.text.toString(),
//                    boardTitleEditText.text.toString(),
//                    boardContentEditText.text.toString(),
//                    "",
//                    0,
//                    SimpleDateFormat("yyyy.MM.dd").format(System.currentTimeMillis()))
                finish()
            }
        }
    }
}