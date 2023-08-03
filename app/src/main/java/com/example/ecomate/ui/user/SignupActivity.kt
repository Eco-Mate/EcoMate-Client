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
                if (signupPasswordEditText.text.toString() == signupPasswordCheckEditText.text.toString()) {
                    signupViewModel.signUp(
                        signupIdEditText.text.toString(),
                        signupPasswordEditText.text.toString(),
                        signupNicknameEditText.text.toString(),
                        signupEmailEditText.text.toString()
                    )
                    Toast.makeText(this.root.context,"회원가입 성공!",Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}