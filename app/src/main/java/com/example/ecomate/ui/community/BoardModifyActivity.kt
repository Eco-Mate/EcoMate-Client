package com.example.ecomate.ui.community

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.Gravity
import android.view.MenuItem
import android.widget.PopupMenu
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.example.ecomate.ApplicationClass
import com.example.ecomate.ApplicationClass.Companion.BOARD_ITEM
import com.example.ecomate.databinding.ActivityBoardAddBinding
import com.example.ecomate.databinding.ActivityBoardModifyBinding
import com.example.ecomate.model.Board
import com.example.ecomate.viewmodel.BoardAddViewModel
import com.example.ecomate.viewmodel.BoardModifyViewModel
import com.example.ecomate.viewmodel.HomeViewModel
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File

class BoardModifyActivity : AppCompatActivity(), PopupMenu.OnMenuItemClickListener {
    lateinit var binding: ActivityBoardModifyBinding
    lateinit var board: Board
    private val boardModifyViewModel: BoardModifyViewModel by viewModels()

    private val permissionList = arrayOf(
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
    )
    private lateinit var imageUri: Uri
    private var challenge_id: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBoardModifyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        board = intent.getSerializableExtra(BOARD_ITEM) as Board

        setUi()
    }

    private fun setUi() {
        // 챌린지 팝업 메뉴 설정
        val popup = PopupMenu(binding.root.context, binding.challengeSelectBtn, Gravity.END)
        boardModifyViewModel.challenges.observe(this@BoardModifyActivity) { challengeList ->
            challengeList.forEach {
                popup.menu.add(0, it.challengeId, 0, it.challengeTitle)
            }
        }
        popup.setOnMenuItemClickListener(this@BoardModifyActivity)

        binding.apply {
            backBtn.setOnClickListener {
                finish()
            }
            boardTitleEditText.setText(board.boardTitle)
            boardContentEditText.setText(board.boardContent)
            // 게시글의 사진 선택 컨트롤
            Glide.with(this.root.context)
                .load(board.image)
                .into(boardImageBtn)
            boardImageBtn.setOnClickListener {
                // 저장소 접근 관련 permission 확인
                if (checkPermission()) {
                    val intent = Intent(Intent.ACTION_PICK)
                    intent.type = "image/*"
                    activityResult.launch(intent)
                } else {
                    requestMultiplePermissions.launch(permissionList)
                }
            }
            // 게시글의 챌린지 종류 선택 컨트롤
            challengeBox.setOnClickListener {
                popup.show()
            }
            challengeSelectBtn.setOnClickListener {
                popup.show()
            }
            // 게시글 수정 버튼 컨트롤
            boardModifyBtn.setOnClickListener {
                boardModifyViewModel.putBoard(
                    board.boardId,
                    boardTitleEditText.text.toString(),
                    boardContentEditText.text.toString()
                )
                finish()
            }
        }
    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        binding.challengeName.text = item?.title
        challenge_id = item?.itemId!!

        return item != null
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
                .into(binding.boardImageBtn)
        }
    }
}