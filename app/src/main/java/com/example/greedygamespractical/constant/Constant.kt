package com.example.greedygamespractical.constant

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.greedygamespractical.R
import com.example.greedygamespractical.helper.DrawableAlwaysCrossFadeFactory
import java.text.SimpleDateFormat
import java.util.*

class Constant {
    companion object {
        val BASE_URL="https://api.themoviedb.org/3/"
        val KEY_BUNDLE = "bundle"
        val IMAGE_BASE_URL = "https://images.tmdb.org/t/p/w500"
        val API_KEY = "1e577d852aafb804c81da4f00e1bb427"


        @JvmStatic
        @BindingAdapter("url")
        fun loadImages(imageView: ImageView, url: String?): Unit {
            Glide.with(imageView.context)
                .load(IMAGE_BASE_URL + url)
                .transition(DrawableTransitionOptions.with(DrawableAlwaysCrossFadeFactory()))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.color.lightGrey)
                .error(R.color.lightGrey)
                .into(imageView)
        }

        @JvmStatic
        @BindingAdapter("setDate")
        fun formateDtae(textView: TextView, date: String?): Unit {
            val fromServer = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
            val myFormat = SimpleDateFormat("MMM dd", Locale.ENGLISH)
            if (date == null && date == "") {
                textView.text = ""
                return
            }
            try {
                val newDate: Date = fromServer.parse(date)
                val outputDateStr: String = myFormat.format(newDate)
                textView.text = outputDateStr
            } catch (e: Exception) {
                textView.text = ""
            }

        }

        @JvmStatic
        @BindingAdapter("setLanguage")
        fun setLanguage(textView: TextView, lan: String?): Unit {
            try {
                val loc = Locale(lan)
                textView.text = loc.getDisplayLanguage(loc)
            } catch (e: Exception) {
                textView.text = ""
            }

        }
    }
}