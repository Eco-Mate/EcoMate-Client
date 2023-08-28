package com.example.ecomate.ui.myprofile

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.ecomate.ApplicationClass
import com.example.ecomate.ApplicationClass.Companion.USER_INFO
import com.example.ecomate.R
import com.example.ecomate.databinding.ActivityFollowInfoBinding
import com.example.ecomate.databinding.ActivityUserProfileBinding
import com.example.ecomate.model.Board
import com.example.ecomate.model.ProfileInfo
import com.example.ecomate.model.User
import com.example.ecomate.ui.adapter.FollowInfoAdapter
import com.example.ecomate.ui.adapter.UserBoardsAdapter
import com.example.ecomate.ui.community.BoardDetailActivity
import com.example.ecomate.viewmodel.FollowInfoViewModel
import com.example.ecomate.viewmodel.MyProfileViewModel
import com.example.ecomate.viewmodel.UserProfileViewModel
import com.google.android.material.tabs.TabLayout
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserProfileActivity : AppCompatActivity() {
    lateinit var binding: ActivityUserProfileBinding
    private val userProfileViewModel: UserProfileViewModel by viewModels()
    lateinit var profileInfo: ProfileInfo
    var followState: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        profileInfo = intent.getSerializableExtra(USER_INFO) as ProfileInfo
        userProfileViewModel.getUserBoards(profileInfo.memberId)
        userProfileViewModel.getUserProfile(profileInfo.memberId)

        setAdapter()
        setUi()
    }
    private fun setAdapter() {
        val userBoardsAdapter = UserBoardsAdapter()
        userBoardsAdapter.detailBoardListener =
            object : UserBoardsAdapter.DetailBoardListener {
                override fun onClick(board: Board) {
                    val intent = Intent(this@UserProfileActivity, BoardDetailActivity::class.java)
                    intent.putExtra(ApplicationClass.BOARD_ITEM, board)
                    startActivity(intent)
                }
            }
        binding.boardRv.apply {
            layoutManager = LinearLayoutManager(this.context)
            adapter = userBoardsAdapter
        }

        userProfileViewModel.userBoards.observe(this@UserProfileActivity) {
            userBoardsAdapter.submitList(it)
        }
    }
    private fun setUi() {
        binding.apply {
            backBtn.setOnClickListener {
                finish()
            }
            userProfileViewModel.profileInfo.observe(this@UserProfileActivity) {
                // 사용자 프로필 설정
                userNickname.text = it.nickname
                if (it.profileImage != null && it.profileImage != "") {
                    Glide.with(this.root.context)
                        .load(it.profileImage)
                        .into(userImage)
                }
                userState.text = it.statusMessage
                userFollower.text = "${it.followerCnt}\n팔로워"
                userFollowing.text = "${it.followingCnt}\n팔로잉"
                // 사용자 트리포인트 설정
                pointLevel.text = "Lv. ${it.level}"
                pointProgressBar.progress = ((it.totalTreePoint/20.0)*100).toInt()
                currentPoint.text = "(${it.totalTreePoint}/20)"
                pointRest.text = "다음 레벨까지 ${(20-it.totalTreePoint)} 트리포인트 남았어요!"
            }

            // 팔로우 버튼 설정
            userProfileViewModel.getFollowState(profileInfo.nickname)
            userProfileViewModel.followState.observe(this@UserProfileActivity) {
                followState = it
                if (followState) {
                    followBtn.setBackgroundColor(Color.parseColor("#787878"))
                } else {
                    followBtn.setBackgroundColor(Color.parseColor("#79C257"))
                }
            }
            followBtn.setOnClickListener {
                if (followState) {
                    followBtn.setBackgroundColor(Color.parseColor("#79C257"))
                    userProfileViewModel.deleteFollowState(profileInfo.nickname)
                    followState = false
                } else {
                    followBtn.setBackgroundColor(Color.parseColor("#787878"))
                    userProfileViewModel.postFollowState(profileInfo.nickname)
                    followState = true
                }
            }
            // 사용자 참여 챌린지 설정

            // 게시글 수 설정
            userProfileViewModel.userBoards.observe(this@UserProfileActivity) {
                boardNum.text = it.size.toString() + "건"
            }
        }
    }
}