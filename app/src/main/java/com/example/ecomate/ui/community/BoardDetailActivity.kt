package com.example.ecomate.ui.community

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.ecomate.databinding.ActivityBoardDetailBinding
import com.example.ecomate.model.Board

class BoardDetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityBoardDetailBinding
    lateinit var item: Board
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBoardDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        item = intent.getSerializableExtra("boardItem") as Board
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
            Glide.with(this@BoardDetailActivity)
                .load(item.image)
                .into(boardImage)
            profileNickname.text = item.nickname
            boardDate.text = item.createdDate.substring(0,4) +
                    "." + item.createdDate.substring(5,7) +
                    "." + item.createdDate.substring(8,10)
            boardLikeNum.text = item.likeCnt.toString()
            boardTitle.text = item.boardTitle
            boardContent.text = item.boardContent
        }
    }
}