package com.example.ecomate.ui.user

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.example.ecomate.databinding.ActivitySignupBinding
import com.example.ecomate.viewmodel.LogInViewModel
import com.example.ecomate.viewmodel.SignupViewModel

class SignupActivity : AppCompatActivity() {
    lateinit var binding: ActivitySignupBinding
    private val signupViewModel: SignupViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUi()
    }

    private fun setUi() {
        signupViewModel.memberId.observe(this) {
            finish()
        }
        binding.apply {
            backBtn.setOnClickListener {
                finish()
            }
            signupBtn.setOnClickListener {
                if (checkRequirePart()) {
                    signupViewModel.signUp(
                        signupIdEditText.text.toString(),
                        signupPasswordEditText.text.toString(),
                        signupNicknameEditText.text.toString(),
                        signupEmailEditText.text.toString(),
                        signupStateEditText.text.toString()
                    )
                    Toast.makeText(this.root.context, "회원가입 성공!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun checkRequirePart(): Boolean {
        binding.apply {
            if (signupIdEditText.text.toString() == "") {
                Toast.makeText(this.root.context, "아이디를 입력하세요!", Toast.LENGTH_SHORT).show()
                return false
            }
            if (signupPasswordEditText.text.toString() == "") {
                Toast.makeText(this.root.context, "비밀번호를 입력하세요!", Toast.LENGTH_SHORT).show()
                return false
            }
            if (signupPasswordCheckEditText.text.toString() == "") {
                Toast.makeText(this.root.context, "비밀번호 확인 부분을 입력하세요!", Toast.LENGTH_SHORT).show()
                return false
            }
            if (signupNicknameEditText.text.toString() == "") {
                Toast.makeText(this.root.context, "닉네임을 입력하세요!", Toast.LENGTH_SHORT).show()
                return false
            }
            if (signupEmailEditText.text.toString() == "") {
                Toast.makeText(this.root.context, "이메일을 입력하세요!", Toast.LENGTH_SHORT).show()
                return false
            }
            if (signupPasswordEditText.text.toString() != signupPasswordCheckEditText.text.toString()) {
                Toast.makeText(this.root.context, "입력한 비밀번호와 재입력한 비밀번호가 다릅니다!", Toast.LENGTH_SHORT).show()
                return false
            }
            return true
        }
    }
}