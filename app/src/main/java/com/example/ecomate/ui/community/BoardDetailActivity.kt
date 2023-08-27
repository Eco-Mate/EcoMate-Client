package com.example.ecomate.ui.community

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.PopupMenu
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.ecomate.ApplicationClass
import com.example.ecomate.ApplicationClass.Companion.BOARD_ITEM
import com.example.ecomate.ApplicationClass.Companion.USER_INFO
import com.example.ecomate.ApplicationClass.Companion.sharedPreferencesUtil
import com.example.ecomate.R
import com.example.ecomate.databinding.ActivityBoardDetailBinding
import com.example.ecomate.model.Board
import com.example.ecomate.model.Comment
import com.example.ecomate.ui.adapter.CommentsAdapter
import com.example.ecomate.ui.myprofile.UserProfileActivity
import com.example.ecomate.viewmodel.BoardDetailViewModel

class BoardDetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityBoardDetailBinding
    lateinit var board: Board
    private val boardDetailViewModel: BoardDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBoardDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        board = intent.getSerializableExtra(BOARD_ITEM) as Board
        boardDetailViewModel.getComments(board.boardId)

        setAdapter()
        setUi()
    }

    private fun setAdapter() {
        val commentsAdapter = CommentsAdapter()
        commentsAdapter.detailCommentListener =
            object : CommentsAdapter.DetailCommentListener {
                override fun onClick(comment: Comment) {
                    if (sharedPreferencesUtil.getMemberId() == comment.memberId) {
                        boardDetailViewModel.deleteComment(comment.commentId)
                        Toast.makeText(this@BoardDetailActivity, "댓글이 삭제되었습니다.", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }

        binding.commentRv.apply {
            layoutManager = LinearLayoutManager(this.context)
            adapter = commentsAdapter
        }
        boardDetailViewModel.comments.observe(this) {
            commentsAdapter.submitList(it)
        }
    }

    private fun setUi() {
        binding.apply {
            backBtn.setOnClickListener {
                finish()
            }
            boardMoreBtn.setOnClickListener {
                setPopUpMenu(this.root.context, it, board)
            }
            // 게시글 작성자의 프로필 데이터 설정
            if (board.profileImage != null && board.profileImage != "") {
                Glide.with(this.root)
                    .load(board.profileImage)
                    .into(profileImage)
            }
            profileNickname.text = board.nickname

            // 게시글 좋아요 버튼 이미지 설정
            if (board.liked) {
                boardLikeBtn.setImageResource(R.drawable.green_hart)
            } else {
                boardLikeBtn.setImageResource(R.drawable.green_border_hart)
            }
            // 게시글 좋아요 버튼 컨트롤
            boardLikeBtn.setOnClickListener {
                if (board.liked) {
                    boardDetailViewModel.apply {
                        postUnlike(board.boardId)
                        unlike.observe(this@BoardDetailActivity) {
                            boardLikeNum.text = it.likeCnt.toString()
                            board.liked = it.liked
                        }
                    }
                    boardLikeBtn.setImageResource(R.drawable.green_border_hart)
                } else {
                    boardDetailViewModel.apply {
                        postLike(board.boardId)
                        like.observe(this@BoardDetailActivity) {
                            boardLikeNum.text = it.likeCnt.toString()
                            board.liked = it.liked
                        }
                    }
                    boardLikeBtn.setImageResource(R.drawable.green_hart)
                }
            }
            // 게시글 데이터 설정
            if (board.image != null && board.image != "") {
                Glide.with(this.root)
                    .load(board.image)
                    .into(boardImage)
            }
            boardDate.text = board.createdDate.substring(0, 4) +
                    "." + board.createdDate.substring(5, 7) +
                    "." + board.createdDate.substring(8, 10)
            boardLikeNum.text = board.likeCnt.toString()
            boardChallengeTitle.text = board.challengeTitle
            boardTitle.text = board.boardTitle
            boardContent.text = board.boardContent
            // 댓글 추가 컨트롤
            commentSendBtn.setOnClickListener {
                boardDetailViewModel.postComment(
                    board.boardId,
                    commentEditText.text.toString()
                )

                commentEditText.text.clear()
                hideKeyboard(this@BoardDetailActivity)
            }
        }
    }

    private fun setPopUpMenu(context: Context, view: View, board: Board) {
        val popUp = PopupMenu(context, view)
        if (sharedPreferencesUtil.getMemberId() == board.memberId) {
            popUp.menuInflater.inflate(R.menu.my_board_menu, popUp.menu)
            popUp.menu.removeItem(R.id.board_move)
            popUp.setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.board_edit -> {
                        val intent = Intent(this, BoardModifyActivity::class.java)
                        intent.putExtra(BOARD_ITEM, board)
                        startActivity(intent)
                    }
                    R.id.board_delete -> {
                        boardDetailViewModel.deleteBoard(board.boardId)
                        Toast.makeText(this, "게시글이 삭제되었습니다",Toast.LENGTH_SHORT).show()
                        finish()
                    }
                }
                false
            }
        } else {
            popUp.menuInflater.inflate(R.menu.board_menu, popUp.menu)
            popUp.menu.removeItem(R.id.board_move)
            popUp.setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.profile_info -> {
                        boardDetailViewModel.getUserProfile(board.memberId)
                        boardDetailViewModel.profileInfo.observe(this) {
                            val intent = Intent(this, UserProfileActivity::class.java)
                            intent.putExtra(USER_INFO, it)
                            startActivity(intent)
                        }
                    }
                    R.id.board_save -> Toast.makeText(context, "게시글 저장", Toast.LENGTH_SHORT).show()
                }
                false
            }
        }
        popUp.show()
    }

    private fun hideKeyboard(activity: Activity) {
        val ims = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        ims.hideSoftInputFromWindow(activity.window.decorView.applicationWindowToken, 0)
    }
}