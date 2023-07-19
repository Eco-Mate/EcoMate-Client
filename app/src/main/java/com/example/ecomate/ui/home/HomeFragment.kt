package com.example.ecomate.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.ecomate.ui.adapter.ChallengeAdapter
import com.example.ecomate.databinding.FragmentHomeBinding
import com.example.ecomate.ui.challenge.ChallengeDetailActivity
import com.example.ecomate.viewmodel.ChallengeViewModel

class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    private val challengeViewModel: ChallengeViewModel by viewModels()
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
    }

    private fun setAdapter() {
        val challengeAdapter = ChallengeAdapter()
        challengeAdapter.detailChallengeListener =
            object : ChallengeAdapter.DetailChallengeListener {
                override fun onClick(challengeId: Int) {
                    startActivity(Intent(activity, ChallengeDetailActivity::class.java))
                }
            }
        binding.challengeAllRv.adapter = challengeAdapter

        challengeViewModel.challengeList.observe(viewLifecycleOwner) {
            challengeAdapter.submitList(it)
        }
    }
}