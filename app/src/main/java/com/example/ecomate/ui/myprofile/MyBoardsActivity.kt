package com.example.ecomate.ui.myprofile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ecomate.ApplicationClass
import com.example.ecomate.databinding.ActivityMyBoardsBinding
import com.example.ecomate.model.Board
import com.example.ecomate.ui.adapter.BoardsAdapter
import com.example.ecomate.ui.community.BoardDetailActivity
import com.example.ecomate.viewmodel.MyBoardsViewModel

class MyBoardsActivity : AppCompatActivity() {
    lateinit var binding: ActivityMyBoardsBinding
    private val myBoardsViewModel: MyBoardsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyBoardsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setAdapter()
        setUi()
    }

    private fun setAdapter() {
        val boardsAdapter = BoardsAdapter()
        boardsAdapter.detailBoardListener =
            object : BoardsAdapter.DetailBoardListener {
                override fun onClick(board: Board) {
                    val intent = Intent(this@MyBoardsActivity, BoardDetailActivity::class.java)
                    intent.putExtra(ApplicationClass.BOARD_ITEM, board)
                    startActivity(intent)
                }
            }

        binding.boardsRv.apply {
            layoutManager = LinearLayoutManager(this.context)
            adapter = boardsAdapter
            addItemDecoration(
                DividerItemDecoration(
                    this.context,
                    LinearLayoutManager.VERTICAL
                )
            )
        }
        myBoardsViewModel.myBoards.observe(this) {
            boardsAdapter.submitList(it)
        }
    }

    private fun setUi() {
        binding.apply {
            backBtn.setOnClickListener {
                finish()
            }
        }
    }
}