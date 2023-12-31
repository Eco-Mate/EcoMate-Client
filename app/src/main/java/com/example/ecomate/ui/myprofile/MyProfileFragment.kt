package com.example.ecomate.ui.myprofile

import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract.Profile
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
import com.example.ecomate.model.ProfileInfo
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
                profileInfo ->
                // 내정보 설정
                if (profileInfo.profileImage != null && profileInfo.profileImage != "") {
                    Glide.with(this.root.context)
                        .load(profileInfo.profileImage)
                        .into(profileImg)
                }
                profileNickname.text = profileInfo.nickname
                profileState.text = profileInfo.statusMessage
                profileFollower.text = "${profileInfo.followerCnt}\n팔로워"
                profileFollowing.text = "${profileInfo.followingCnt}\n팔로잉"
                // 내 챌린지 포인트 설정
                myProfileViewModel.getLevelInfo(profileInfo.level)
                pointLevel.text = "${profileInfo.level}"
                myProfileViewModel.levelInfo.observe(viewLifecycleOwner) { levelInfo ->
                    pointProgressBar.progress =
                        ((profileInfo.totalTreePoint / levelInfo.goalTreePoint.toDouble()) * 100).toInt()
                    currentPoint.text = "(${profileInfo.totalTreePoint}/${levelInfo.goalTreePoint})"
                    pointRest.text =
                        "다음 레벨까지 ${(levelInfo.goalTreePoint - profileInfo.totalTreePoint)} 트리포인트 남았어요!"
                }
            }

            // 팔로워
            profileFollower.setOnClickListener {
                var intent = Intent(activity, FollowInfoActivity::class.java)
                intent.putExtra("userNickname", profileNickname.text)
                startActivity(intent)
            }

            // 팔로잉
            profileFollowing.setOnClickListener {
                var intent = Intent(activity, FollowInfoActivity::class.java)
                intent.putExtra("userNickname", profileNickname.text)
                startActivity(intent)
            }

            // 내 챌린지
            myProfileViewModel.myAllChallenges.observe(viewLifecycleOwner) {
                challengeNum.text = it.size.toString() + "건"
                if (it.size > 0) {
                    challengeEmptyLayout.visibility = View.INVISIBLE
                    currentChallenge.visibility = View.VISIBLE
                    if (it[0].image != null && it[0].image != "") {
                        Glide.with(this.root.context)
                            .load(it[0].image)
                            .into(challengeImage)
                    }
                    challengeName.text = it[0].challengeTitle
                    challengeProgressBar.progress = ((it[0].doneCnt / it[0].goalCnt.toFloat()) * 100).toInt()
                    challengeProgressCount.text =
                        "${((it[0].doneCnt / it[0].goalCnt.toFloat()) * 100).toInt()}% 달성 (${it[0].doneCnt}회/${it[0].goalCnt}회)"
                } else {
                    challengeEmptyLayout.visibility = View.VISIBLE
                    currentChallenge.visibility = View.INVISIBLE
                }
            }
            challengeBtn.setOnClickListener {
                startActivity(Intent(activity, MyChallengesActivity::class.java))
            }

            // 내 게시물
            myProfileViewModel.myBoards.observe(viewLifecycleOwner) {
                boardNum.text = it.size.toString() + "건"
                // 게시물 1
                if (it.size == 0) {
                    boardEmptyLayout.visibility = View.VISIBLE
                    board1.visibility = View.INVISIBLE
                    board2.visibility = View.INVISIBLE
                    board3.visibility = View.INVISIBLE
                }
                if (it.size >= 1) {
                    boardEmptyLayout.visibility = View.INVISIBLE
                    board1.visibility = View.VISIBLE
                    board2.visibility = View.INVISIBLE
                    board3.visibility = View.INVISIBLE
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
                    boardEmptyLayout.visibility = View.INVISIBLE
                    board1.visibility = View.VISIBLE
                    board2.visibility = View.VISIBLE
                    board3.visibility = View.INVISIBLE
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
                    boardEmptyLayout.visibility = View.INVISIBLE
                    board1.visibility = View.VISIBLE
                    board2.visibility = View.VISIBLE
                    board3.visibility = View.VISIBLE
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
                startActivity(Intent(activity, MyBoardsActivity::class.java))
            }

            // 저장한 게시물
            box4.setOnClickListener {
                startActivity(Intent(activity, SavedBoardsActivity::class.java))
            }
            saveBoardBtn.setOnClickListener {
                startActivity(Intent(activity, SavedBoardsActivity::class.java))
            }

            // 저장한 에코 매장
            box5.setOnClickListener {
                startActivity(Intent(activity, SavedEchoshopsActivity::class.java))
            }
            saveEchoshopBtn.setOnClickListener {
                startActivity(Intent(activity, SavedEchoshopsActivity::class.java))
            }

            // 포리필 편집
            box6.setOnClickListener {
                startActivity(Intent(activity, EditProfileActivity::class.java))
            }
            editProfileBtn.setOnClickListener {
                startActivity(Intent(activity, EditProfileActivity::class.java))
            }

            // 오픈소스 라이센스
            box7.setOnClickListener {

            }

            // 로그아웃
            box9.setOnClickListener {
                sharedPreferencesUtil.deleteToken()
                startActivity(Intent(activity, LoginActivity::class.java))
                activity?.finish()
            }
        }
    }
}