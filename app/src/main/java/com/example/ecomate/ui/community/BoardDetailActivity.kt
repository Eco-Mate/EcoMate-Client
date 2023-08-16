package com.example.ecomate.ui.community

import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.ecomate.ApplicationClass.Companion.BOARD_ITEM
import com.example.ecomate.ApplicationClass.Companion.sharedPreferencesUtil
import com.example.ecomate.R
import com.example.ecomate.databinding.ActivityBoardDetailBinding
import com.example.ecomate.model.Board
import com.example.ecomate.model.BoardLike
import com.example.ecomate.model.Comment
import com.example.ecomate.model.CommentPost
import com.example.ecomate.ui.adapter.BoardCommentAdapter
import com.example.ecomate.viewmodel.BoardCommentViewModel
import com.example.ecomate.viewmodel.BoardDeleteCommentViewModel
import com.example.ecomate.viewmodel.BoardLikeViewModel
import com.example.ecomate.viewmodel.BoardPostCommentViewModel
import com.example.ecomate.viewmodel.BoardUnlikeViewModel

class BoardDetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityBoardDetailBinding
    lateinit var board: Board
    private val boardCommentViewModel: BoardCommentViewModel by viewModels()
    private val boardLikeViewModel: BoardLikeViewModel by viewModels()
    private val boardUnlikeViewModel: BoardUnlikeViewModel by viewModels()
    private val boardPostCommentViewModel: BoardPostCommentViewModel by viewModels()
    private val boardDeleteCommentViewModel: BoardDeleteCommentViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBoardDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        board = intent.getSerializableExtra(BOARD_ITEM) as Board
        boardCommentViewModel.getBoardComment(board.boardId)

        setAdapter()
        setUi()
    }

    private fun setAdapter() {
        val boardCommentAdapter = BoardCommentAdapter()
        boardCommentAdapter.detailCommentListener =
            object : BoardCommentAdapter.DetailCommentListener {
                override fun onClick(comment: Comment) {
                    if (sharedPreferencesUtil.getMemberId() == comment.memberId) {
                        boardDeleteCommentViewModel.deleteComment(comment.commentId)
                        Toast.makeText(this@BoardDetailActivity,"댓글이 삭제되었습니다.",Toast.LENGTH_SHORT).show()
                    }
                }
            }

        binding.commentRv.apply {
            layoutManager = LinearLayoutManager(this.context)
            adapter = boardCommentAdapter
        }
        boardCommentViewModel.boardComment.observe(this) {
            boardCommentAdapter.submitList(it)
        }
    }
    private fun setUi() {
        binding.apply {
            backBtn.setOnClickListener {
                finish()
            }
            if (board.image != null && board.image != "") {
                Glide.with(this.root)
                    .load(board.image)
                    .into(boardImage)
            }
            if (board.profileImage != null && board.profileImage != "") {
                Glide.with(this.root)
                    .load(board.profileImage)
                    .into(profileImage)
            }
            profileNickname.text = board.nickname
            boardDate.text = board.createdDate.substring(0,4) +
                    "." + board.createdDate.substring(5,7) +
                    "." + board.createdDate.substring(8,10)
            if (board.liked) {
                boardLikeBtn.setImageResource(R.drawable.green_hart)
            } else {
                boardLikeBtn.setImageResource(R.drawable.green_border_hart)
            }
            boardLikeBtn.setOnClickListener {
                if (board.liked) {
                    boardUnlikeViewModel.apply {
                        postBoardUnlike(BoardLike(board.boardId))
                        boardUnlike.observe(this@BoardDetailActivity) {
                            boardLikeNum.text = it.likeCnt.toString()
                            board.liked = it.liked
                        }
                    }
                    boardLikeBtn.setImageResource(R.drawable.green_border_hart)
                } else {
                    boardLikeViewModel.apply {
                        postBoardLike(BoardLike(board.boardId))
                        boardLike.observe(this@BoardDetailActivity) {
                            boardLikeNum.text = it.likeCnt.toString()
                            board.liked = it.liked
                        }
                    }
                    boardLikeBtn.setImageResource(R.drawable.green_hart)
                }
            }
            boardLikeNum.text = board.likeCnt.toString()
            boardChallengeTitle.text = board.challengeTitle
            boardTitle.text = board.boardTitle
            boardContent.text = board.boardContent
            commentSendBtn.setOnClickListener {
                boardPostCommentViewModel.postComment(CommentPost(board.boardId, commentEditText.text.toString()))
                commentEditText.text.clear()
                hideKeyboard(this@BoardDetailActivity)
            }
        }
    }

    private fun hideKeyboard(activity: Activity) {
        val ims = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        ims.hideSoftInputFromWindow(activity.window.decorView.applicationWindowToken, 0)
    }
}