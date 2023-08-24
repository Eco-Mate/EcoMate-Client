package com.example.ecomate.ui.challenge

import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.ecomate.databinding.ActivityEditChallengeBinding
import com.example.ecomate.model.ChallengeDto
import com.example.ecomate.viewmodel.EditChallengeViewModel
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import java.io.FileOutputStream

class EditChallengeActivity : AppCompatActivity() {
    lateinit var binding: ActivityEditChallengeBinding
    private lateinit var selectedImageUri: Uri
    private val editChallengeViewModel: EditChallengeViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditChallengeBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.apply {
            editIv.setOnClickListener {
                openGallery()
            }
            postChallengeBtn.setOnClickListener {

                editChallengeViewModel.postChallenge(
                    ChallengeDto(
                        radioButtonTrue.isChecked,
                        editTitle.text.toString(),
                        editContent.text.toString(),
                        editGoalCnt.text.toString().toInt(),
                        editTreePoint.text.toString().toInt()
                    ), uploadChallengeWithImage()
                )
                finish()
            }
            toolbar.setNavigationOnClickListener {
                finish()
            }
        }
    }

    private fun openGallery() {
        galleryLauncher.launch("image/*")
    }
    private val galleryLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        if (uri != null) {
            selectedImageUri = uri
            binding.editIv.setImageURI(selectedImageUri)
            // 선택한 이미지를 사용하여 업로드 등 작업 수행
        }
    }
    private fun uploadChallengeWithImage(): MultipartBody.Part {
        val imageFile = File(cacheDir, "temp_image.jpg")
        val outputStream = FileOutputStream(imageFile)
        val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, selectedImageUri)
        bitmap.compress(Bitmap.CompressFormat.JPEG, 90, outputStream)
        outputStream.close()

        val requestBody = imageFile.asRequestBody("image/jpeg".toMediaTypeOrNull())
        return MultipartBody.Part.createFormData("file", imageFile.name, requestBody)
    }
}