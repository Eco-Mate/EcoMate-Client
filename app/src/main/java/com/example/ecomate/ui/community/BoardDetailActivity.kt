package com.example.ecomate.ui.community

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.ecomate.databinding.ActivityBoardDetailBinding
import com.example.ecomate.model.Board
import com.example.ecomate.ui.adapter.BoardCommentAdapter
import com.example.ecomate.viewmodel.BoardCommentViewModel
import com.example.ecomate.viewmodel.DetailChallengeViewModel

class BoardDetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityBoardDetailBinding
    lateinit var board: Board
    private val boardCommentViewModel: BoardCommentViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBoardDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        boardCommentViewModel.getBoardComment(intent.getIntExtra("boardId", 0))
        board = intent.getSerializableExtra("boardItem") as Board

        setAdapter()
        setUi()
    }

    private fun setAdapter() {
        boardCommentViewModel.boardComment.observe(this) {
            binding.commentRv.apply {
                layoutManager = LinearLayoutManager(this.context)
                adapter = BoardCommentAdapter(it)
                addItemDecoration(
                    DividerItemDecoration(
                        this.context,
                        LinearLayoutManager.VERTICAL)
                )
            }
        }
    }
    private fun setUi() {
        binding.apply {
            backBtn.setOnClickListener {
                finish()
            }
            Glide.with(this@BoardDetailActivity)
                .load(board.image)
                .into(boardImage)
            profileNickname.text = board.nickname
            boardDate.text = board.createdDate.substring(0,4) +
                    "." + board.createdDate.substring(5,7) +
                    "." + board.createdDate.substring(8,10)
            boardLikeNum.text = board.likeCnt.toString()
            boardChallengeTitle.text = board.challengeTitle
            boardTitle.text = board.boardTitle
            boardContent.text = board.boardContent
        }
    }
}