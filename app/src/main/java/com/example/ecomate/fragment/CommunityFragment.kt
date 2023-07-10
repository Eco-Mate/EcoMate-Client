package com.example.ecomate.fragment

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ecomate.R
import com.example.ecomate.adapter.CommunityAdapter
import com.example.ecomate.databinding.FragmentCommunityBinding

class CommunityFragment(val dataSet: MutableList<String>) : Fragment() {
    lateinit var binding: FragmentCommunityBinding
    var isOpened = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCommunityBinding.inflate(inflater, container, false)

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
        binding.optionSearch.setOnClickListener { Toast.makeText(inflater.context, "Search Button", Toast.LENGTH_SHORT).show() }
        binding.optionAdd.setOnClickListener { Toast.makeText(inflater.context, "Add Button", Toast.LENGTH_SHORT).show() }

        // 커뮤니티 게시물 관련 Recyclerview
        binding.recyclerView.layoutManager = LinearLayoutManager(layoutInflater.context)

        binding.recyclerView.adapter = CommunityAdapter(dataSet)

        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(layoutInflater.context,
            LinearLayoutManager.VERTICAL)
        )

//        dataSet.add("지구지키삼")
//        (binding.recyclerView.adapter as CommunityAdapter).notifyDataSetChanged()

        return binding.root
    }
}