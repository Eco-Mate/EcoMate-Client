package com.example.ecomate.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.ecomate.ApplicationClass.Companion.CHALLENGE_ID
import com.example.ecomate.databinding.FragmentHomeBinding
import com.example.ecomate.ui.adapter.HomeChallengeAllAdapter
import com.example.ecomate.ui.adapter.MyProgressChallengeAllAdapter
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
        binding.challengeCompleteTv.text =
            "지금까지\\n${homeViewModel.finishMyChallengeCount.value}개의 챌린지를 완료했어요"
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
                    val intent = Intent(activity, ChallengeDetailActivity::class.java)
                    intent.putExtra(CHALLENGE_ID, challengeId)
                    startActivity(intent)
                }
            }
        binding.challengeAllRv.adapter = homeChallengeAllAdapter

        homeViewModel.challengeList.observe(viewLifecycleOwner) {
            homeChallengeAllAdapter.submitList(it)
        }

        val myProgressChallengeAllAdapter = MyProgressChallengeAllAdapter()
        myProgressChallengeAllAdapter.detailMyProgressChallengeListener =
            object : MyProgressChallengeAllAdapter.DetailMyProgressChallengeListener {
                override fun onClick(challengeId: Int) {
                    val intent = Intent(activity, ChallengeDetailActivity::class.java)
                    intent.putExtra(CHALLENGE_ID, challengeId)
                    startActivity(intent)
                }
            }
        binding.challengeProgressRv.adapter = myProgressChallengeAllAdapter

        homeViewModel.progressMyChallengeList.observe(viewLifecycleOwner) {
            myProgressChallengeAllAdapter.submitList(it)
        }
    }
}