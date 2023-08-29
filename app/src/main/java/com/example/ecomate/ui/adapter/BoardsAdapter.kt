package com.example.ecomate.ui.adapter

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ecomate.ApplicationClass.Companion.sharedPreferencesUtil
import com.example.ecomate.R
import com.example.ecomate.databinding.ItemBoardBinding
import com.example.ecomate.model.Board

class BoardsAdapter : ListAdapter<Board, BoardsAdapter.BoardsViewHolder>(
    BoardAllDiffCallback()
) {
    private lateinit var binding: ItemBoardBinding

    inner class BoardsViewHolder(private val binding: ItemBoardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @RequiresApi(Build.VERSION_CODES.Q)
        fun setBind(board: Board) {
            binding.apply {
                if (board.profileImage != null && board.profileImage != "") {
                    Glide.with(this.root)
                        .load(board.profileImage)
                        .into(profileImg)
                }
                profileNickname.text = board.nickname
                boardDate.text = board.createdDate.substring(0, 4) +
                        "." + board.createdDate.substring(5, 7) +
                        "." + board.createdDate.substring(8, 10)
                profileMore.setOnClickListener {
                    setPopUpMenu(this.root.context, it, board)
                }
                profileBox.setOnClickListener {
                    profileInfoListener.onClick(board = board)
                }
                Glide.with(this.root)
                    .load(board.image)
                    .into(boardImg)
                boardContent.text = board.boardContent
                root.setOnClickListener {
                    detailBoardListener.onClick(board = board)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BoardsViewHolder {
        binding = ItemBoardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BoardsViewHolder(binding)
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onBindViewHolder(holder: BoardsViewHolder, position: Int) {
        holder.setBind(getItem(position))
    }

    interface DetailBoardListener {
        fun onClick(board: Board)
    }
    interface ModifyBoardListener {
        fun onClick(board: Board)
    }
    interface DeleteBoardListener {
        fun onClick(board: Board)
    }
    interface ProfileInfoListener {
        fun onClick(board: Board)
    }

    lateinit var detailBoardListener: DetailBoardListener
    lateinit var modifyBoardListener: ModifyBoardListener
    lateinit var deleteBoardListener: DeleteBoardListener
    lateinit var profileInfoListener: ProfileInfoListener

    @RequiresApi(Build.VERSION_CODES.Q)
    private fun setPopUpMenu(context: Context, view: View, board: Board) {
        val popUp = PopupMenu(context, view)
        if (sharedPreferencesUtil.getMemberId() == board.memberId) {
            popUp.menuInflater.inflate(R.menu.my_board_menu, popUp.menu)
            popUp.setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.board_move -> detailBoardListener.onClick(board = board)
                    R.id.board_edit -> modifyBoardListener.onClick(board = board)
                    R.id.board_delete -> deleteBoardListener.onClick(board = board)
                }
                false
            }
        } else {
            popUp.menuInflater.inflate(R.menu.board_menu, popUp.menu)
            popUp.menu.removeItem(R.id.board_save)
            popUp.setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.profile_info -> profileInfoListener.onClick(board = board)
                    R.id.board_move -> detailBoardListener.onClick(board = board)
                }
                false
            }
        }
        popUp.show()
    }
}

class BoardAllDiffCallback : DiffUtil.ItemCallback<Board>() {
    override fun areItemsTheSame(oldItem: Board, newItem: Board): Boolean {
        return oldItem.boardId == newItem.boardId
    }

    override fun areContentsTheSame(oldItem: Board, newItem: Board): Boolean {
        return oldItem == newItem
    }
}