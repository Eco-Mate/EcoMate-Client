package com.example.ecomate.ui.util

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.ecomate.R

object Util {

    fun loadImg(context: Context, url: String, view: ImageView) {
        Glide.with(context)
            .load(url)
            .fallback(R.drawable.baseline_delete_24)
            .into(view)

    }

}