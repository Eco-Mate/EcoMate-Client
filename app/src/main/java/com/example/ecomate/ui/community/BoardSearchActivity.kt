package com.example.ecomate.ui.community

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.ecomate.ApplicationClass.Companion.BOARD_ITEM
import com.example.ecomate.databinding.ActivityBoardSearchBinding
import com.example.ecomate.model.Board
import com.example.ecomate.ui.adapter.BoardSearchAdapter
import com.example.ecomate.viewmodel.BoardSearchViewModel

class BoardSearchActivity : AppCompatActivity() {
    lateinit var binding: ActivityBoardSearchBinding
    private val boardSearchViewModel: BoardSearchViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBoardSearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setAdapter()
        setUi()
    }

    private fun setAdapter() {
        val boardSearchAdapter = BoardSearchAdapter()
        boardSearchAdapter.detailBoardListener =
            object : BoardSearchAdapter.DetailBoardListener {
                override fun onClick(board: Board) {
                    val intent = Intent(
                        this@BoardSearchActivity,
                        BoardDetailActivity::class.java
                    )
                    intent.putExtra(BOARD_ITEM, board)
                    startActivity(intent)
                }
            }

        binding.boardRv.apply {
            layoutManager = GridLayoutManager(context, 3)
            adapter = boardSearchAdapter
        }
        boardSearchViewModel.boards.observe(this) {
            boardSearchAdapter.submitList(it)
        }
    }

    private fun setUi() {
        binding.backBtn.setOnClickListener {
            finish()
        }
        // 검색 내용 입력
    }
}