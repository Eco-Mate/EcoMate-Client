package com.example.ecomate.ui.myprofile

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.PopupMenu
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.registerForActivityResult
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.example.ecomate.R
import com.example.ecomate.databinding.ActivityEditProfileBinding
import com.example.ecomate.ui.LoadingDialog
import com.example.ecomate.viewmodel.EditProfileViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import java.io.FileOutputStream

class EditProfileActivity : AppCompatActivity() {
    lateinit var binding: ActivityEditProfileBinding
    private lateinit var selectedImageUri: Uri
    private val editProfileViewModel: EditProfileViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUi()
    }

    private fun setUi() {
        binding.apply {
            backBtn.setOnClickListener {
                finish()
            }
            editProfileViewModel.profileInfo.observe(this@EditProfileActivity) {
                if (it.profileImage != null && it.profileImage != "") {
                    Glide.with(this.root.context)
                        .load(it.profileImage)
                        .into(currentImg)
                }
                editNicknameEditText.setText(it.nickname)
                editEmailEditText.setText(it.email)
                editStateEditText.setText(it.statusMessage)
            }
            editImgTitle.setOnClickListener {
                setPopUpMenu(this.root.context, it)
            }
            editImgBtn.setOnClickListener {
                setPopUpMenu(this.root.context, it)
            }
            editNicknameBtn.setOnClickListener {
                editProfileViewModel.postMyProfileInfo(
                    editNicknameEditText.text.toString(),
                    editEmailEditText.text.toString(),
                    editStateEditText.text.toString()
                )
                Toast.makeText(this.root.context,"닉네임이 변경되었습니다.",Toast.LENGTH_SHORT).show()
            }
            editPasswordBtn.setOnClickListener {

            }
            editEmailBtn.setOnClickListener {
                editProfileViewModel.postMyProfileInfo(
                    editNicknameEditText.text.toString(),
                    editEmailEditText.text.toString(),
                    editStateEditText.text.toString()
                )
                Toast.makeText(this.root.context,"이메일이 변경되었습니다.",Toast.LENGTH_SHORT).show()
            }
            editStateBtn.setOnClickListener {
                editProfileViewModel.postMyProfileInfo(
                    editNicknameEditText.text.toString(),
                    editEmailEditText.text.toString(),
                    editStateEditText.text.toString()
                )
                Toast.makeText(this.root.context,"상태메세지가 변경되었습니다.",Toast.LENGTH_SHORT).show()
            }
        }

        val dialog = LoadingDialog(this)
        editProfileViewModel.isLoading.observe(this) {
            if (editProfileViewModel.isLoading.value!!) {
                dialog.show()
            } else if (!editProfileViewModel.isLoading.value!!) {
                dialog.dismiss()
            }
        }
    }

    private fun setPopUpMenu(context: Context, view: View) {
        val popUp = PopupMenu(context, view)
        popUp.menuInflater.inflate(R.menu.profile_image_edit_menu, popUp.menu)
        popUp.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.image_select -> openGallery()

                R.id.image_modify -> editProfileViewModel.postMyProfileImage(postMyProfileImage())

                R.id.image_remove -> {
                    binding.currentImg.setImageResource(R.drawable.profile_image_2)
                    editProfileViewModel.deleteMyProfileImage()
                }
            }
            false
        }
        popUp.show()
    }

    private fun openGallery() {
        galleryLauncher.launch("image/*")
    }
    private val galleryLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        if (uri != null) {
            selectedImageUri = uri
            binding.currentImg.setImageURI(selectedImageUri)
        }
    }
    private fun postMyProfileImage(): MultipartBody.Part {
        val imageFile = File(cacheDir, "temp_image.jpg")
        val outputStream = FileOutputStream(imageFile)
        val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, selectedImageUri)
        bitmap.compress(Bitmap.CompressFormat.JPEG, 90, outputStream)
        outputStream.close()

        val requestBody = imageFile.asRequestBody("image/jpeg".toMediaTypeOrNull())
        return MultipartBody.Part.createFormData("file", imageFile.name, requestBody)
    }
}