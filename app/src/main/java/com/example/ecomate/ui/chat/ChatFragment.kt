package com.example.ecomate.ui.chat

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ecomate.R
import com.example.ecomate.databinding.ChatEditDialogBinding
import com.example.ecomate.databinding.ChatRemoveDialogBinding
import com.example.ecomate.databinding.FragmentChatBinding
import com.example.ecomate.model.ChatInfoItem
import com.example.ecomate.model.RoomNameBody
import com.example.ecomate.ui.adapter.ChatAdapter
import com.example.ecomate.viewmodel.ChatViewModel

class ChatFragment : Fragment() {
    lateinit var binding: FragmentChatBinding
    val chatViewModel: ChatViewModel by viewModels()
    private lateinit var chatRemoveDialog: Dialog
    private lateinit var chatEditDialog: Dialog
//    var chatList: List<Chat> = mutableListOf(
//        Chat(
//            0,
//            "",
//            "지구지키기 방범대 1번 방",
//            mutableListOf(
//                ChatMember(0,"","짱구"),
//                ChatMember(1,"","철수"),
//                ChatMember(2,"","유리"),
//                ChatMember(3,"","훈이"),
//                ChatMember(4,"","맹구"),
//            )),
//        Chat(
//            1,
//            "",
//            "지구지키기 방범대 2번 방",
//            mutableListOf(
//                ChatMember(0,"","짱구"),
//                ChatMember(1,"","철구"),
//                ChatMember(2,"","유수"),
//                ChatMember(3,"","훈리"),
//                ChatMember(4,"","맹이"),
//            )),
//
//    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChatBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter(view)
        setUi()
    }

    override fun onResume() {
        super.onResume()
        chatViewModel.getChatList()
    }

    private fun setAdapter(view: View) {
        val chatAdapter = ChatAdapter()
        chatAdapter.detailChatListener =
            object : ChatAdapter.DetailChatListener {
                override fun onClick(chatInfoItem: ChatInfoItem) {
                    val intent = Intent(activity, ChatDetailActivity::class.java)
                    intent.putExtra("chatInfoItem", chatInfoItem)
                    startActivity(intent)
                }
            }

        chatAdapter.popUpChatListener =
            object : ChatAdapter.PopUpChatListener {
                override fun onClick(v: View, roomId: Int) {
                    setPopUpMenu(requireContext(), v)
                    setChatRemoveDialog(binding.chatRv)
                    setChatEditDialog(binding.chatRv, roomId)
                }
            }



        binding.chatRv.apply {
            layoutManager = LinearLayoutManager(view.context)
            adapter = chatAdapter
        }
        chatViewModel.chatList.observe(viewLifecycleOwner) {
            chatAdapter.submitList(it)
        }

    }

    private fun setUi() {
        binding.apply {
            chatAdd.setOnClickListener {
                startActivity(Intent(activity, ChatAddActivity::class.java))
            }
        }
    }

    private fun setPopUpMenu(context: Context, view: View) {
        val popUp = PopupMenu(context, view)
        popUp.menuInflater.inflate(R.menu.chat_menu, popUp.menu)
        popUp.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.chat_remove -> chatRemoveDialog.show()
                R.id.chat_name_edit -> chatEditDialog.show()
            }
            false
        }
        popUp.show()
    }

    private fun setChatRemoveDialog(parent: ViewGroup) {
        val chatRemoveDialogBinding =
            ChatRemoveDialogBinding.inflate(LayoutInflater.from(parent.context))
        chatRemoveDialog = Dialog(chatRemoveDialogBinding.root.context)

        chatRemoveDialogBinding.apply {
            checkBtn.setOnClickListener {
                chatRemoveDialog.dismiss()
            }
            cancelBtn.setOnClickListener {
                chatRemoveDialog.dismiss()
            }
        }
        chatRemoveDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        chatRemoveDialog.setContentView(chatRemoveDialogBinding.root)
    }

    private fun setChatEditDialog(parent: ViewGroup, roomId: Int) {
        val chatEditDialogBinding =
            ChatEditDialogBinding.inflate(LayoutInflater.from(parent.context))
        chatEditDialog = Dialog(chatEditDialogBinding.root.context)

        chatEditDialogBinding.apply {
            checkBtn.setOnClickListener {
                chatViewModel.modifyRoomName(roomId, RoomNameBody(nameEt.text.toString()))
                chatEditDialog.dismiss()
            }
            cancelBtn.setOnClickListener {
                chatEditDialog.dismiss()
            }
        }
        chatEditDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        chatEditDialog.setContentView(chatEditDialogBinding.root)
    }
}