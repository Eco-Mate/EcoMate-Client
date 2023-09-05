package com.example.ecomate.ui.map

import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.Gravity
import android.view.MenuItem
import android.widget.PopupMenu
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import com.example.ecomate.databinding.ActivityBoardAddBinding
import com.example.ecomate.databinding.ActivityEcostoreAddBinding
import com.example.ecomate.model.BoardDto
import com.example.ecomate.model.StoreDto
import com.example.ecomate.ui.LoadingDialog
import com.example.ecomate.viewmodel.BoardAddViewModel
import com.example.ecomate.viewmodel.EcostoreAddViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import java.io.FileOutputStream

class EcostoreAddActivity : AppCompatActivity() {
    lateinit var binding: ActivityEcostoreAddBinding
    private val ecostoreAddViewModel: EcostoreAddViewModel by viewModels()
    private lateinit var selectedImageUri: Uri


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEcostoreAddBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUi()
    }

    private fun setUi() {
        binding.apply {
            backBtn.setOnClickListener {
                finish()
            }
            // 매장 사진 선택 컨트롤
            ecostoreImageBtn.setOnClickListener {
                openGallery()
            }
            // 매장의 키워드 선택 컨트롤
            keywordBox.setOnClickListener {

            }
            keywordSelectBtn.setOnClickListener {

            }
            // 매장 등록 버튼 컨트롤
            ecostoreAddBtn.setOnClickListener {
                ecostoreAddViewModel.postEcoStore(
                    StoreDto(
                        ecostoreNameEt.text.toString(),
                        ecostoreDescEt.text.toString(),
                        ecostoreLatitudeEt.text.toString().toDouble(),
                        ecostoreLongitudeEt.text.toString().toDouble(),
                        ecostoreAddressEt.text.toString()
                    ), postEcostoreWithImage(), this@EcostoreAddActivity
                )
            }
        }

        val dialog = LoadingDialog(this)
        ecostoreAddViewModel.isLoading.observe(this) {
            if (ecostoreAddViewModel.isLoading.value!!) {
                dialog.show()
            } else if (!ecostoreAddViewModel.isLoading.value!!) {
                dialog.dismiss()
            }
        }
    }

    private fun openGallery() {
        galleryLauncher.launch("image/*")
    }
    private val galleryLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) {
        uri: Uri? ->
        if (uri != null) {
            selectedImageUri = uri
            binding.ecostoreImageBtn.setImageURI(selectedImageUri)
        }
    }
    private fun postEcostoreWithImage(): MultipartBody.Part {
        val imageFile = File(cacheDir, "temp_image.jpg")
        val outputStream = FileOutputStream(imageFile)
        val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, selectedImageUri)
        bitmap.compress(Bitmap.CompressFormat.JPEG, 90, outputStream)
        outputStream.close()

        val requestBody = imageFile.asRequestBody("image/jpeg".toMediaTypeOrNull())
        return MultipartBody.Part.createFormData("file", imageFile.name, requestBody)
    }
}