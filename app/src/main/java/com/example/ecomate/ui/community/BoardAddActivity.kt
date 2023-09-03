package com.example.ecomate.ui.community

import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.Gravity
import android.view.MenuItem
import android.widget.PopupMenu
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.ecomate.databinding.ActivityBoardAddBinding
import com.example.ecomate.model.BoardDto
import com.example.ecomate.ui.LoadingDialog
import com.example.ecomate.viewmodel.BoardAddViewModel
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import java.io.FileOutputStream

class BoardAddActivity : AppCompatActivity(), PopupMenu.OnMenuItemClickListener {
    lateinit var binding: ActivityBoardAddBinding
    private val boardAddViewModel: BoardAddViewModel by viewModels()
    private lateinit var selectedImageUri: Uri
    private var challenge_id: Int = -1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBoardAddBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUi()
    }

    private fun setUi() {
        // 챌린지 팝업 메뉴 설정
        val popup = PopupMenu(binding.root.context, binding.challengeSelectBtn, Gravity.END)
        boardAddViewModel.challenges.observe(this@BoardAddActivity) { challengeList ->
            if (challengeList.size > 0) {
                challengeList.forEach {
                    popup.menu.add(0, it.challengeId, 0, it.challengeTitle)
                }
            } else {
                popup.menu.add(0, -1, 0, "진행 중인 챌린지 없음")
            }
        }
        popup.setOnMenuItemClickListener(this@BoardAddActivity)

        binding.apply {
            backBtn.setOnClickListener {
                finish()
            }
            // 게시글의 사진 선택 컨트롤
            boardImageBtn.setOnClickListener {
                openGallery()
            }
            // 게시글의 챌린지 종류 선택 컨트롤
            challengeBox.setOnClickListener {
                popup.show()
            }
            challengeSelectBtn.setOnClickListener {
                popup.show()
            }
            // 게시글 작성 버튼 컨트롤
            boardAddBtn.setOnClickListener {
                boardAddViewModel.postBoard(
                    BoardDto(
                        challenge_id,
                        boardTitleEditText.text.toString(),
                        boardContentEditText.text.toString()
                    ), postBoardWithImage(), this@BoardAddActivity
                )
                Log.e("하위",challenge_id.toString())
                boardAddViewModel.updateMyChallenge(challenge_id)
            }
        }

        val dialog = LoadingDialog(this)
        boardAddViewModel.isLoading.observe(this) {
            if (boardAddViewModel.isLoading.value!!) {
                dialog.show()
            } else if (!boardAddViewModel.isLoading.value!!) {
                dialog.dismiss()
            }
        }
    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        if (item?.itemId != -1) {
            challenge_id = item?.itemId!!
            binding.challengeName.text = item?.title
        }

        return item != null
    }

    private fun openGallery() {
        galleryLauncher.launch("image/*")
    }

    private val galleryLauncher =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            if (uri != null) {
                selectedImageUri = uri
                binding.boardImageBtn.setImageURI(selectedImageUri)
            }
        }

    private fun postBoardWithImage(): MultipartBody.Part {
        val imageFile = File(cacheDir, "temp_image.jpg")
        val outputStream = FileOutputStream(imageFile)
        val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, selectedImageUri)
        bitmap.compress(Bitmap.CompressFormat.JPEG, 90, outputStream)
        outputStream.close()

        val requestBody = imageFile.asRequestBody("image/jpeg".toMediaTypeOrNull())
        return MultipartBody.Part.createFormData("file", imageFile.name, requestBody)
    }
}