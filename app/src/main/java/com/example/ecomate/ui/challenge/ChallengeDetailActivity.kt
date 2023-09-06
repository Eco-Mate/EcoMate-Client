package com.example.ecomate.ui.challenge

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.ecomate.ApplicationClass
import com.example.ecomate.databinding.ActivityChallengeDetailBinding
import com.example.ecomate.ui.LoadingDialog
import com.example.ecomate.ui.util.Util.loadImg
import com.example.ecomate.viewmodel.DetailChallengeViewModel

class ChallengeDetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityChallengeDetailBinding
    private val detailChallengeViewModel: DetailChallengeViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChallengeDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUI()
    }

    private fun setUI() {
        val mode = intent.getIntExtra("mode", 0)
        if (mode == 2) {//mychallegne
            detailChallengeViewModel.getDetailMyChallenge(intent.getIntExtra("myChallengeId", 0))
            detailChallengeViewModel.myChallengeDetail.observe(this) {
                binding.apply {
                    challengeDetailTitleTv.text = it.challengeTitle
                    challengeContent.text = it.description
                    if (it.image != null && it.image != "") {
                        loadImg(this@ChallengeDetailActivity, it.image, binding.challengeDetailIv)
                    }
                    challengeDetailSuccessTv.text = "${it.treePoint}포인트"
                    challengeDetailInfoTv.text = "${it.goalCnt}회"


                    challengeProgressBar.visibility = View.VISIBLE
                    challengeProgressDes.visibility = View.VISIBLE

                    challengeProgressBar.max = it.goalCnt
                    challengeProgressBar.progress = it.doneCnt

                    val temp = (it.doneCnt.toFloat() / it.goalCnt.toFloat()) * 100

                    challengeProgressDes.text =
                        "${temp.toInt()}% 달성 (${it.doneCnt}회/${it.goalCnt}회)"
                    binding.challengeDetailSelectBtn.text = "포기하기"
                    binding.challengeDetailSelectBtn.setOnClickListener { view ->
                        detailChallengeViewModel.deleteMyChallenge(
                            it.myChallengeId,
                            this@ChallengeDetailActivity
                        )
                    }
                }
            }

        } else {
            detailChallengeViewModel.getDetailChallenge(intent.getIntExtra("challengeId", 0))
            detailChallengeViewModel.challengeDetail.observe(this) {
                binding.apply {
                    challengeDetailTitleTv.text = it.challengeTitle
                    challengeContent.text = it.description
                    if (it.image != null && it.image != "") {
                        loadImg(this@ChallengeDetailActivity, it.image, binding.challengeDetailIv)
                    }
                    challengeDetailSuccessTv.text = "${it.treePoint}포인트"
                    challengeDetailInfoTv.text = "${it.goalCnt}회"

                    if (ApplicationClass.sharedPreferencesUtil.getMemberId() == 1) {
                        binding.challengeDetailSelectBtn.text =
                            if (it.activeYn) "비활성화하기" else "활성화하기"
                        challengeDetailSelectBtn.setOnClickListener { view ->
                            detailChallengeViewModel.updateActiveYn(!it.activeYn, it.challengeId)
                            finish()
                        }
                    } else {
                        challengeDetailSelectBtn.setOnClickListener { view ->
                            detailChallengeViewModel.tryChallenge(
                                it.challengeId,
                                this@ChallengeDetailActivity
                            )
                            //finish()
                        }
                    }
                    binding.challengeDetailDelete.setOnClickListener { view ->
                        detailChallengeViewModel.deleteChallenge(
                            it.challengeId,
                            this@ChallengeDetailActivity
                        )
                        //finish()
                    }
                }
            }
        }


        val dialog = LoadingDialog(this)
        detailChallengeViewModel.isLoading.observe(this) {
            if (detailChallengeViewModel.isLoading.value!!) {
                dialog.show()
            } else if (!detailChallengeViewModel.isLoading.value!!) {
                dialog.dismiss()
            }
        }

        if (ApplicationClass.sharedPreferencesUtil.getMemberId() == 1) {
            binding.challengeDetailDelete.visibility = View.VISIBLE
        }
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }
    }
}