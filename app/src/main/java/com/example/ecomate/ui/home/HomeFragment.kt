package com.example.ecomate.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.ecomate.databinding.FragmentHomeBinding
import com.example.ecomate.ui.adapter.HomeChallengeAllAdapter
import com.example.ecomate.ui.challenge.ChallengeActivity
import com.example.ecomate.ui.challenge.ChallengeDetailActivity
import com.example.ecomate.ui.challenge.EditChallengeActivity
import com.example.ecomate.viewmodel.HomeViewModel

class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    private val homeViewModel: HomeViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter()
        setUi()
    }

    private fun setUi() {
        binding.challengeEditBtn.setOnClickListener {
            startActivity(Intent(activity, EditChallengeActivity::class.java))
        }
        binding.challengeALlLayout.setOnClickListener {
            startActivity(Intent(activity, ChallengeActivity::class.java))
        }
    }

    private fun setAdapter() {
        val homeChallengeAllAdapter = HomeChallengeAllAdapter()
        homeChallengeAllAdapter.detailHomeChallengeListener =
            object : HomeChallengeAllAdapter.DetailHomeChallengeListener {
                override fun onClick(challengeId: Int) {
                    startActivity(Intent(activity, ChallengeDetailActivity::class.java))
                }
            }
        binding.challengeAllRv.adapter = homeChallengeAllAdapter

        homeViewModel.challengeList.observe(viewLifecycleOwner) {
            homeChallengeAllAdapter.submitList(it)

        }
    }
}