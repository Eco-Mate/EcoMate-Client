package com.example.ecomate.viewmodel

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.ecomate.ApplicationClass
import org.json.JSONObject
import ua.naiksoftware.stomp.Stomp
import ua.naiksoftware.stomp.StompClient
import ua.naiksoftware.stomp.dto.LifecycleEvent
import ua.naiksoftware.stomp.dto.StompHeader

class ChatDetailViewModel : ViewModel() {
    val token = ApplicationClass.sharedPreferencesUtil.getAccessToken()
    lateinit var stompClient: StompClient

    @SuppressLint("CheckResult")
    fun runStomp(roomId: Int) {
        stompClient =
            Stomp.over(
                Stomp.ConnectionProvider.OKHTTP,
                "ws://15.164.103.242:8081/api/ecomate-ws",

                )
        val headerList = arrayListOf<StompHeader>()
        headerList.add(StompHeader("Authorization", token))
        stompClient.connect(headerList)

        stompClient.topic("/topic/chat/${roomId}").subscribe { topicMessage ->
            Log.d("message Receive", topicMessage.payload)
        }


        stompClient.lifecycle().subscribe { lifecycleEvent ->
            when (lifecycleEvent.type) {
                LifecycleEvent.Type.OPENED -> {
                    Log.d("OPENED", "opened")
                }

                LifecycleEvent.Type.CLOSED -> {
                    Log.d("CLOSED", "closed")
                }

                LifecycleEvent.Type.ERROR -> {
                    Log.d("ERROR", "error")
                    Log.i("CONNECT ERROR", lifecycleEvent.exception.toString())
                }

                else -> {
                    Log.d("else", lifecycleEvent.message)
                }
            }
        }
    }

    fun sendStomp(msg: String, roomId: Int) {
        val data = JSONObject()
        data.put("message", msg)
        data.put("chatType", 0)

        stompClient.send("/pub/chat/${roomId}", data.toString()).subscribe()
        Log.d("Message Send", "내가 보낸거: " + msg)
    }
}