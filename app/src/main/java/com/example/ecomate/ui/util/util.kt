package com.example.ecomate.ui.util

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide

object Util {

    fun loadImg(context: Context, url: String, view: ImageView) {
        Glide.with(context)
            .load(url)
            .into(view)
    }

}