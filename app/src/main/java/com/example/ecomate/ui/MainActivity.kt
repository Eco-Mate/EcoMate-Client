package com.example.ecomate.ui

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.content.pm.Signature
import android.os.Build
import android.os.Bundle
import android.util.Base64
import android.util.Log
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.ecomate.ApplicationClass.Companion.sharedPreferencesUtil
import com.example.ecomate.R
import com.example.ecomate.databinding.ActivityMainBinding
import com.example.ecomate.ui.chat.ChatFragment
import com.example.ecomate.ui.community.CommunityFragment
import com.example.ecomate.ui.home.HomeFragment
import com.example.ecomate.ui.map.MapFragment
import com.example.ecomate.ui.myprofile.MyProfileFragment
import com.example.ecomate.viewmodel.MainViewModel
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.tabs.TabLayout
import com.google.firebase.messaging.FirebaseMessaging
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    val mainViewModel: MainViewModel by viewModels()
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        getFcmToken()
        getHashKey()
        setUi()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getFcmToken() {
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                return@OnCompleteListener
            }
            // token log 남기기
            if (task.result != null) {
                mainViewModel.uploadToken(task.result!!)
            }
        })
        createNotificationChannel(channel_id, "ecoMate")
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel(id: String, name: String) {
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(id, name, importance)

        val notificationManager: NotificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }

    override fun onDestroy() {
        super.onDestroy()
        sharedPreferencesUtil.deleteToken()
    }

    private fun setUi() {
        supportFragmentManager.beginTransaction().add(R.id.tab_content, HomeFragment()).commit()
        binding.tabs.addOnTabSelectedListener(
            object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    val transaction = supportFragmentManager.beginTransaction()
                    when (tab?.text) {
                        "홈" -> transaction.replace(R.id.tab_content, HomeFragment())
                        "커뮤니티" -> transaction.replace(R.id.tab_content, CommunityFragment())
                        "에코챗" -> transaction.replace(R.id.tab_content, ChatFragment())
                        "에코맵" -> transaction.replace(R.id.tab_content, MapFragment())
                        "내정보" -> transaction.replace(R.id.tab_content, MyProfileFragment())
                    }
                    transaction.commit()
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {
                    Log.d("TabButton", "onTabUnselected...")
                }

                override fun onTabReselected(tab: TabLayout.Tab?) {
                    Log.d("TabButton", "onTabReselected...")
                }
            }
        )
    }

    private fun getHashKey() {
        var packageInfo: PackageInfo? = null
        try {
            packageInfo = packageManager.getPackageInfo(packageName, PackageManager.GET_SIGNATURES)
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }
        if (packageInfo == null)
            Log.e("KeyHash", "KeyHash:null")
        for (signature: Signature in packageInfo!!.signatures) {
            try {
                var md: MessageDigest = MessageDigest.getInstance("SHA")
                md.update(signature.toByteArray())
                Log.d("KeyHash", Base64.encodeToString(md.digest(), Base64.DEFAULT))
            } catch (e: NoSuchAlgorithmException) {
                Log.e("KeyHash", "Unable to get MessageDigest. signature=" + signature, e)
            }
        }
    }

    companion object {
        const val channel_id = "ecoMate_channel"
    }
}