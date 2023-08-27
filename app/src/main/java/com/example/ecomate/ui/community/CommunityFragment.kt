package com.example.ecomate.ui.community

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ecomate.ApplicationClass.Companion.BOARD_ITEM
import com.example.ecomate.ApplicationClass.Companion.USER_INFO
import com.example.ecomate.R
import com.example.ecomate.databinding.FragmentCommunityBinding
import com.example.ecomate.model.Board
import com.example.ecomate.ui.adapter.BoardsAdapter
import com.example.ecomate.ui.myprofile.UserProfileActivity
import com.example.ecomate.viewmodel.CommunityViewModel

class CommunityFragment : Fragment() {
    lateinit var binding: FragmentCommunityBinding
    private val communityViewModel: CommunityViewModel by viewModels()
    var isOpened = false
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCommunityBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUi()
        setAdapter(view)
    }

    private fun setUi() {
        // FloatingActionButton 이벤트 설정
        val boardAddAniOut =
            ObjectAnimator.ofFloat(binding.boardAdd, "translationY", -400f).setDuration(500)
        val boardAddAniIn =
            ObjectAnimator.ofFloat(binding.boardAdd, "translationY", 0f).setDuration(500)

        val boardSearchAniOut =
            ObjectAnimator.ofFloat(binding.boardSearch, "translationY", -200f).setDuration(500)
        val boardSearchAniIn =
            ObjectAnimator.ofFloat(binding.boardSearch, "translationY", 0f).setDuration(500)

        binding.apply {
            moreOption.setOnClickListener {
                // FloatingActionButton 컨트롤
                if (isOpened) {
                    binding.moreOption.setImageResource(R.drawable.white_more)
                    boardSearchAniIn.start()
                    boardAddAniIn.start()
                } else {
                    binding.moreOption.setImageResource(R.drawable.white_down_arrow)
                    boardSearchAniOut.start()
                    boardAddAniOut.start()
                }
                isOpened = !isOpened
            }

            // 게시글 추가 버튼 컨트롤
            boardAdd.setOnClickListener {
                startActivity(Intent(activity, BoardAddActivity::class.java))
            }
            // 게시글 검색 버튼 컨트롤
            boardSearch.setOnClickListener {
                startActivity(Intent(activity, BoardSearchActivity::class.java))
            }

        }
    }

    private fun setAdapter(view: View) {
        val boardsAdapter = BoardsAdapter()
        boardsAdapter.detailBoardListener =
            object : BoardsAdapter.DetailBoardListener {
                override fun onClick(board: Board) {
                    val intent = Intent(activity, BoardDetailActivity::class.java)
                    intent.putExtra(BOARD_ITEM, board)
                    startActivity(intent)
                }
            }
        boardsAdapter.modifyBoardListener =
            object : BoardsAdapter.ModifyBoardListener {
                override fun onClick(board: Board) {
                    val intent = Intent(activity, BoardModifyActivity::class.java)
                    intent.putExtra(BOARD_ITEM, board)
                    startActivity(intent)
                }
            }
        boardsAdapter.deleteBoardListener =
            object : BoardsAdapter.DeleteBoardListener {
                override fun onClick(board: Board) {
                    communityViewModel.deleteBoard(board.boardId)
                    Toast.makeText(this@CommunityFragment.context, "게시글이 삭제되었습니다",Toast.LENGTH_SHORT).show()
                }
            }
        boardsAdapter.profileInfoListener =
            object : BoardsAdapter.ProfileInfoListener {
                override fun onClick(board: Board) {
                    communityViewModel.getUserProfile(board.memberId)
                    communityViewModel.profileInfo.observe(viewLifecycleOwner) {
                        val intent = Intent(activity, UserProfileActivity::class.java)
                        intent.putExtra(USER_INFO, it)
                        startActivity(intent)
                    }
                }
            }

        binding.boardRv.apply {
            layoutManager = LinearLayoutManager(view.context)
            adapter = boardsAdapter
            addItemDecoration(
                DividerItemDecoration(
                    view.context,
                    LinearLayoutManager.VERTICAL
                )
            )
        }

        communityViewModel.boards.observe(viewLifecycleOwner) {
            boardsAdapter.submitList(it)
        }
    }
}