package com.example.ecomate.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecomate.model.ChatInfoItem
import com.example.ecomate.network.RetrofitUtil
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import ua.naiksoftware.stomp.StompClient

class ChatViewModel : ViewModel() {
    private val _chatList = MutableLiveData<List<ChatInfoItem>>()
    val chatList: LiveData<List<ChatInfoItem>>
        get() = _chatList
    private lateinit var webSocket: WebSocket
    private fun connectWebSocket() {
        val client = OkHttpClient()

        val request = Request.Builder()
            .url("ws://your-server-url/chat") // WebSocket 서버 주소
            .build()

        webSocket = client.newWebSocket(request, object : WebSocketListener() {
            override fun onOpen(webSocket: WebSocket, response: Response) {
                // WebSocket 연결 성공
            }

            override fun onMessage(webSocket: WebSocket, text: String) {
                // 서버로부터 메시지 수신 처리
            }

            override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
                // WebSocket 종료됨
            }

            override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
                // 연결 실패 또는 에러 처리
            }
        })
    }


    init {
        getChatList()
    }

    fun getChatList() {
        viewModelScope.launch {
            _chatList.value = RetrofitUtil.chatApi.getChatList().response
        }
    }

}