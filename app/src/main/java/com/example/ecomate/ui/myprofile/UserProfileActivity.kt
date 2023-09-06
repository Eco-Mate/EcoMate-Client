package com.example.ecomate.ui.myprofile

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.ecomate.ApplicationClass
import com.example.ecomate.ApplicationClass.Companion.USER_INFO
import com.example.ecomate.databinding.ActivityUserProfileBinding
import com.example.ecomate.model.Board
import com.example.ecomate.model.ProfileInfo
import com.example.ecomate.ui.adapter.UserBoardsAdapter
import com.example.ecomate.ui.community.BoardDetailActivity
import com.example.ecomate.viewmodel.UserProfileViewModel

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
        userProfileViewModel.getUserChallenges(profileInfo.memberId)

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
            userProfileViewModel.profileInfo.observe(this@UserProfileActivity) { profileInfo ->
                // 사용자 프로필 설정
                userNickname.text = profileInfo.nickname
                if (profileInfo.profileImage != null && profileInfo.profileImage != "") {
                    Glide.with(this.root.context)
                        .load(profileInfo.profileImage)
                        .into(userImage)
                }
                userState.text = profileInfo.statusMessage
                userFollower.text = "${profileInfo.followerCnt}\n팔로워"
                userFollowing.text = "${profileInfo.followingCnt}\n팔로잉"
                // 사용자 트리포인트 설정
                userProfileViewModel.getLevelInfo(profileInfo.level)
                pointLevel.text = "${profileInfo.level}"
                userProfileViewModel.levelInfo.observe(this@UserProfileActivity) { levelInfo ->
                    pointProgressBar.progress =
                        ((profileInfo.totalTreePoint / levelInfo.goalTreePoint.toDouble()) * 100).toInt()
                    currentPoint.text = "(${profileInfo.totalTreePoint}/${levelInfo.goalTreePoint})"
                    pointRest.text =
                        "다음 레벨까지 ${(levelInfo.goalTreePoint - profileInfo.totalTreePoint)} 트리포인트 남았어요!"
                }
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
            userProfileViewModel.userChallenges.observe(this@UserProfileActivity) {
                if (it.size > 0) {
                    currentChallenge.visibility = View.VISIBLE
                    challengeEmptyLayout.visibility = View.INVISIBLE
                    challengeNum.text = it.size.toString() + "\n참여 챌린지 수"
                    if (it[0].image != null && it[0].image != "") {
                        Glide.with(this.root.context)
                            .load(it[0].image)
                            .into(challengeImage)
                    }
                    challengeName.text = it[0].challengeTitle
                    challengeProgressBar.progress = (it[0].doneCnt / it[0].goalCnt) * 100
                    challengeProgressCount.text =
                        "${((it[0].doneCnt / it[0].goalCnt.toFloat()) * 100).toInt()}% 달성 (${it[0].doneCnt}회/${it[0].goalCnt}회)"
                } else {
                    challengeNum.text = "0\n참여 챌린지 수"
                    currentChallenge.visibility = View.INVISIBLE
                    challengeEmptyLayout.visibility = View.VISIBLE
                }
            }
            // 게시글 수 설정
            userProfileViewModel.userBoards.observe(this@UserProfileActivity) {
                boardNum.text = it.size.toString() + "건"
            }
        }
    }
}