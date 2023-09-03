package com.example.ecomate.ui.util

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager
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
    fun hideKeyboard(activity: Activity) {
        val ims = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        ims.hideSoftInputFromWindow(activity.window.decorView.applicationWindowToken, 0)
    }

}