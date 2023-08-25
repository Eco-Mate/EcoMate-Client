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
        detailChallengeViewModel.getDetailChallenge(intent.getIntExtra("challengeId", 0))

        setUI()
    }

    private fun setUI() {
        detailChallengeViewModel.challengeDetail.observe(this) {
            binding.apply {
                toolbar.title = it.challengeTitle
                challengeContent.text = it.description
                loadImg(this@ChallengeDetailActivity, it.image, binding.challengeDetailIv)
                challengeDetailInfoTv.text = "성공보상 : ${it.treePoint}\n 목표횟수 : ${it.goalCnt}"

                if (ApplicationClass.sharedPreferencesUtil.getMemberId() == 10) {
                    binding.challengeDetailSelectBtn.text =
                        if (it.activeYn) "비활성화하기" else "활성화하기"
                    challengeDetailSelectBtn.setOnClickListener { view ->
                        detailChallengeViewModel.updateActiveYn(!it.activeYn, it.challengeId)
                        finish()
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

        val dialog = LoadingDialog(this)
        detailChallengeViewModel.isLoading.observe(this) {
            if (detailChallengeViewModel.isLoading.value!!) {
                dialog.show()
            } else if (!detailChallengeViewModel.isLoading.value!!) {
                dialog.dismiss()
            }
        }

        if (ApplicationClass.sharedPreferencesUtil.getMemberId() == 10) {
            binding.challengeDetailDelete.visibility = View.VISIBLE
        }
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }
    }
}