package com.example.ecomate.ui.myprofile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.ecomate.databinding.FragmentMyprofileBinding
import com.example.ecomate.ui.user.LoginActivity

class MyProfileFragment : Fragment() {
    lateinit var binding: FragmentMyprofileBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMyprofileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUi()
    }

    private fun setUi() {
        binding.apply {
            // 팔로워
            profileFollower.setOnClickListener {

            }

            // 팔로잉
            profileFollowing.setOnClickListener {

            }

            // 챌린지 포인트
            pointBox.setOnClickListener {

            }

            // 내 챌린지
            challengeBtn.setOnClickListener {

            }

            // 내 게시물
            boardBtn.setOnClickListener {
                startActivity(Intent(activity,MyBoardsActivity::class.java))
            }

            // 저장한 게시물
            box4.setOnClickListener {
                startActivity(Intent(activity,SaveBoardsActivity::class.java))
            }
            saveBoardBtn.setOnClickListener {
                startActivity(Intent(activity,SaveBoardsActivity::class.java))
            }

            // 저장한 에코 매장
            box5.setOnClickListener {
                startActivity(Intent(activity,SaveEchoshopsActivity::class.java))
            }
            saveEchoshopBtn.setOnClickListener {
                startActivity(Intent(activity,SaveEchoshopsActivity::class.java))
            }

            // 포리필 편집
            box6.setOnClickListener {
                startActivity(Intent(activity,EditProfileActivity::class.java))
            }
            editProfileBtn.setOnClickListener {
                startActivity(Intent(activity,EditProfileActivity::class.java))
            }

            // 오픈소스 라이센스
            box7.setOnClickListener {

            }

            // 로그아웃
            box9.setOnClickListener {
                activity?.finish()
            }
        }
    }
}