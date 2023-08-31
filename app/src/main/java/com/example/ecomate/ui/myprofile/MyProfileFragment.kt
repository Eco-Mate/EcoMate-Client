package com.example.ecomate.ui.myprofile

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.ecomate.ApplicationClass
import com.example.ecomate.ApplicationClass.Companion.sharedPreferencesUtil
import com.example.ecomate.databinding.FragmentMyprofileBinding
import com.example.ecomate.model.Board
import com.example.ecomate.ui.community.BoardDetailActivity
import com.example.ecomate.ui.user.LoginActivity
import com.example.ecomate.viewmodel.MyProfileViewModel

class MyProfileFragment : Fragment() {
    lateinit var binding: FragmentMyprofileBinding
    private val myProfileViewModel: MyProfileViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMyprofileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUi()
    }

    override fun onResume() {
        super.onResume()
        myProfileViewModel.getMyProfile()
        myProfileViewModel.getMyAllChallenge()
        myProfileViewModel.getMyBoards()
    }

    private fun setUi() {
        binding.apply {
            myProfileViewModel.profileInfo.observe(viewLifecycleOwner) {
                // 내정보 설정
                if (it.profileImage != null && it.profileImage != "") {
                    Glide.with(this.root.context)
                        .load(it.profileImage)
                        .into(profileImg)
                }
                profileNickname.text = it.nickname
                profileState.text = it.statusMessage
                profileFollower.text = "${it.followerCnt}\n팔로워"
                profileFollowing.text = "${it.followingCnt}\n팔로잉"
                // 내 챌린지 포인트 설정
                pointLevel.text = "Lv. ${it.level}"
                pointProgressBar.progress = ((it.totalTreePoint/20.0)*100).toInt()
                currentPoint.text = "(${it.totalTreePoint}/20)"
                pointRest.text = "다음 레벨까지 ${(20-it.totalTreePoint)} 트리포인트 남았어요!"
            }
            // 팔로워
            profileFollower.setOnClickListener {
                var intent = Intent(activity, FollowInfoActivity::class.java)
                intent.putExtra("userNickname",profileNickname.text)
                startActivity(intent)
            }

            // 팔로잉
            profileFollowing.setOnClickListener {
                var intent = Intent(activity, FollowInfoActivity::class.java)
                intent.putExtra("userNickname",profileNickname.text)
                startActivity(intent)
            }

            // 내 챌린지
            myProfileViewModel.myAllChallenges.observe(viewLifecycleOwner) {
                if (it.size > 0) {
                    challengeNum.text = it.size.toString() + "건"
                    if (it[0].image != null && it[0].image != "") {
                        Glide.with(this.root.context)
                            .load(it[0].image)
                            .into(challengeImage)
                    }
                    challengeName.text = it[0].challengeTitle
                    challengeProgressBar.progress = (it[0].doneCnt/it[0].goalCnt)*100
                    challengeProgressCount.text = "${((it[0].doneCnt/it[0].goalCnt.toFloat())*100).toInt()}% 달성 (${it[0].doneCnt}회/${it[0].goalCnt}회)"
                }
            }
            challengeBtn.setOnClickListener {
                startActivity(Intent(activity,MyChallengesActivity::class.java))
            }

            // 내 게시물
            myProfileViewModel.myBoards.observe(viewLifecycleOwner) {
                boardNum.text = it.size.toString() + "건"
                // 게시물 1
                if (it.size >= 1) {
                    val board: Board = it[0]
                    Glide.with(this@MyProfileFragment)
                        .load(board.image)
                        .into(board1Image)
                    board1Title.text = board.boardTitle
                    board1.setOnClickListener {
                        val intent = Intent(activity, BoardDetailActivity::class.java)
                        intent.putExtra(ApplicationClass.BOARD_ITEM, board)
                        startActivity(intent)
                    }
                }
                // 게시물 2
                if (it.size >= 2) {
                    val board: Board = it[1]
                    Glide.with(this@MyProfileFragment)
                        .load(board.image)
                        .into(board2Image)
                    board2Title.text = board.boardTitle
                    board2.setOnClickListener {
                        val intent = Intent(activity, BoardDetailActivity::class.java)
                        intent.putExtra(ApplicationClass.BOARD_ITEM, board)
                        startActivity(intent)
                    }
                }
                // 게시물 3
                if (it.size >= 3) {
                    val board: Board = it[2]
                    Glide.with(this@MyProfileFragment)
                        .load(board.image)
                        .into(board3Image)
                    board3Title.text = board.boardTitle
                    board3.setOnClickListener {
                        val intent = Intent(activity, BoardDetailActivity::class.java)
                        intent.putExtra(ApplicationClass.BOARD_ITEM, board)
                        startActivity(intent)
                    }
                }
            }
            boardBtn.setOnClickListener {
                startActivity(Intent(activity,MyBoardsActivity::class.java))
            }

            // 저장한 게시물
            box4.setOnClickListener {
                startActivity(Intent(activity,SavedBoardsActivity::class.java))
            }
            saveBoardBtn.setOnClickListener {
                startActivity(Intent(activity,SavedBoardsActivity::class.java))
            }

            // 저장한 에코 매장
            box5.setOnClickListener {
                startActivity(Intent(activity,SavedEchoshopsActivity::class.java))
            }
            saveEchoshopBtn.setOnClickListener {
                startActivity(Intent(activity,SavedEchoshopsActivity::class.java))
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
                sharedPreferencesUtil.deleteToken()
                startActivity(Intent(activity,LoginActivity::class.java))
                activity?.finish()
            }
        }
    }
}