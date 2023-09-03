package com.example.ecomate.ui.community

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.KeyEvent
import android.view.KeyEvent.KEYCODE_ENTER
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.ecomate.ApplicationClass.Companion.BOARD_ITEM
import com.example.ecomate.databinding.ActivityBoardSearchBinding
import com.example.ecomate.model.Board
import com.example.ecomate.ui.adapter.BoardSearchAdapter
import com.example.ecomate.ui.util.Util.hideKeyboard
import com.example.ecomate.viewmodel.BoardSearchViewModel
import retrofit2.HttpException
import java.lang.Exception

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
        binding.apply {
            backBtn.setOnClickListener {
                finish()
            }
            // 검색 내용 입력
            searchBtn.setOnClickListener {
                if (boardSearchEditText.text.toString() == "") {
                    boardSearchViewModel.getBoards()
                } else {
                    boardSearchViewModel.getSearchBoards(boardSearchEditText.text.toString())
                    boardSearchEditText.text.clear()
                    hideKeyboard(this@BoardSearchActivity)
                }
            }
        }
    }

    private fun hideKeyboard(activity: Activity) {
        val ims = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        ims.hideSoftInputFromWindow(activity.window.decorView.applicationWindowToken, 0)
    }
}