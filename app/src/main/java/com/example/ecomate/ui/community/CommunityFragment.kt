package com.example.ecomate.ui.community

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ecomate.ApplicationClass.Companion.BOARD_ITEM
import com.example.ecomate.R
import com.example.ecomate.databinding.FragmentCommunityBinding
import com.example.ecomate.model.Board
import com.example.ecomate.ui.adapter.BoardAllAdapter
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
        // FloatingActionButton 컨트롤
        val boardAddAniOut = ObjectAnimator.ofFloat(binding.boardAdd, "translationY", -300f).setDuration(500)
        val boardAddAniIn = ObjectAnimator.ofFloat(binding.boardAdd, "translationY", 0f).setDuration(500)

        val boardSearchAniOut = ObjectAnimator.ofFloat(binding.boardSearch, "translationY", -150f).setDuration(500)
        val boardSearchAniIn = ObjectAnimator.ofFloat(binding.boardSearch, "translationY", 0f).setDuration(500)

        binding.apply {
            moreOption.setOnClickListener {
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

            boardAdd.setOnClickListener {
                startActivity(Intent(activity, BoardAddActivity::class.java))
            }
            boardSearch.setOnClickListener {
                startActivity(Intent(activity, BoardSearchActivity::class.java))
            }

        }
    }

    private fun setAdapter(view: View) {
        communityViewModel.boardList.observe(viewLifecycleOwner) {
            val boardAllAdapter = BoardAllAdapter(it)
            boardAllAdapter.detailBoardListener =
                object : BoardAllAdapter.DetailBoardListener {
                    override fun onClick(board: Board) {
                        val intent = Intent(activity, BoardDetailActivity::class.java)
                        intent.putExtra(BOARD_ITEM, board)
                        startActivity(intent)
                    }
                }

            binding.boardRv.apply {
                layoutManager = LinearLayoutManager(view.context)
                adapter = boardAllAdapter
                addItemDecoration(
                    DividerItemDecoration(
                        view.context,
                        LinearLayoutManager.VERTICAL)
                )
            }
        }
    }
}