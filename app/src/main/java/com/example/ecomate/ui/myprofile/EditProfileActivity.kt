package com.example.ecomate.ui.myprofile

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
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
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.example.ecomate.R
import com.example.ecomate.databinding.ActivityEditProfileBinding
import com.example.ecomate.viewmodel.EditProfileViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

class EditProfileActivity : AppCompatActivity() {
    lateinit var binding: ActivityEditProfileBinding
    private val editProfileViewModel: EditProfileViewModel by viewModels()

    private val permissionList = arrayOf(
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
    )
    private lateinit var imageUri: Uri

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUi()
    }

    private fun setUi() {
        binding.apply {
            editProfileViewModel.profileInfo.observe(this@EditProfileActivity) {
                editNicknameEditText.setText(it.nickname)
                editEmailEditText.setText(it.email)
                editStateEditText.setText(it.statusMessage)
            }
            backBtn.setOnClickListener {
                finish()
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
    }

    private fun setPopUpMenu(context: Context, view: View) {
        val popUp = PopupMenu(context, view)
        popUp.menuInflater.inflate(R.menu.profile_image_edit_menu, popUp.menu)
        popUp.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.image_select -> {
                    val intent = Intent(Intent.ACTION_PICK)
                    intent.type = "image/*"
                    activityResult.launch(intent)
                }
                R.id.image_modify -> {
                    if (checkPermission()) {
                        postMyProfileImage()
                    } else {
                        requestMultiplePermissions.launch(permissionList)
                    }
                }
                R.id.image_remove -> {
                    binding.currentImg.setImageResource(R.drawable.profile_image_2)
                    editProfileViewModel.deleteMyProfileImage()
                }
            }
            false
        }
        popUp.show()
    }

    private fun checkPermission(): Boolean {
        var status = true
        permissionList.forEach {
            if (ContextCompat.checkSelfPermission(this, it) == PackageManager.PERMISSION_DENIED) {
                status = false
            }
        }

        return status
    }

    private val requestMultiplePermissions =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { results ->
            results.forEach {
                if (!it.value) {
                    Toast.makeText(applicationContext, "${it.key} 권한 허용 필요", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }

    private val activityResult: ActivityResultLauncher<Intent> = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == RESULT_OK && it.data != null) {
            imageUri = it.data!!.data!!

            // ImageButton 선택한 이미지 적용
            Glide.with(this)
                .load(imageUri)
                .into(binding.currentImg)
        }
    }
    private fun postMyProfileImage() {
        // Image File의 절대 경로 변환
        var cursor = contentResolver.query(imageUri!!, null, null, null, null)
        cursor?.moveToNext()
        val path =
            cursor?.getString(cursor?.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)!!)!!
        cursor?.close()

        // 이미지 파일 MultipartBody.Part로 변환
        val imageFile = File(path!!)
        val requestBody = imageFile.asRequestBody("image/*".toMediaTypeOrNull())
        val file = MultipartBody.Part.createFormData("file", imageFile.name, requestBody)
        editProfileViewModel.postMyProfileImage(file)
    }
}