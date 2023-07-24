package com.example.ecomate.ui.community

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ecomate.ApplicationClass.Companion.BOARD_ID
import com.example.ecomate.R
import com.example.ecomate.databinding.FragmentCommunityBinding
import com.example.ecomate.model.Board
import com.example.ecomate.ui.adapter.CommunityBoardAllAdapter
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
        val optionAddAniOut = ObjectAnimator.ofFloat(binding.optionAdd, "translationY", -300f).setDuration(500)
        val optionAddAniIn = ObjectAnimator.ofFloat(binding.optionAdd, "translationY", 0f).setDuration(500)

        val optionSearchAniOut = ObjectAnimator.ofFloat(binding.optionSearch, "translationY", -150f).setDuration(500)
        val optionSearchAniIn = ObjectAnimator.ofFloat(binding.optionSearch, "translationY", 0f).setDuration(500)

        binding.moreOption.setOnClickListener{
            if (isOpened) {
                binding.moreOption.setImageResource(R.drawable.white_more)
                optionSearchAniIn.start()
                optionAddAniIn.start()
            } else {
                binding.moreOption.setImageResource(R.drawable.down_arrow)
                optionSearchAniOut.start()
                optionAddAniOut.start()
            }
            isOpened = !isOpened
        }

        // FloatingActionButton 클릭 이벤트
        binding.optionSearch.setOnClickListener {
            startActivity(Intent(activity, PostSearchActivity::class.java))
        }

        binding.optionAdd.setOnClickListener {
            startActivity(Intent(activity, PostAddActivity::class.java))
        }
    }

    private fun setAdapter(view: View) {
        communityViewModel.boardList.observe(viewLifecycleOwner) {
            val communityBoardAllAdapter = CommunityBoardAllAdapter(it)
            communityBoardAllAdapter.detailBoardListener =
                object : CommunityBoardAllAdapter.DetailBoardListener {
                    override fun onClick(boardId: Int) {
                        val intent = Intent(activity, PostDetailActivity::class.java)
                        intent.putExtra(BOARD_ID, boardId)
                        startActivity(intent)
                    }
                }

            binding.recyclerView.layoutManager = LinearLayoutManager(view.context)

            binding.recyclerView.adapter = communityBoardAllAdapter

            binding.recyclerView.addItemDecoration(
                DividerItemDecoration(
                    view.context,
                    LinearLayoutManager.VERTICAL)
            )
        }
    }
}