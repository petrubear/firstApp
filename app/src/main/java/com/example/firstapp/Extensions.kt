package com.example.firstapp

import android.content.Context
import android.view.LayoutInflater.from
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import com.squareup.picasso.Picasso

fun Context.toast(message: String, length: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, length).show()
}


fun MediaAdapter.ViewHolder.toast(message: String, length: Int = Toast.LENGTH_SHORT) =
    this.itemView.context.toast(message, length)
//fun MediaAdapter.ViewHolder.toast(message: String) {
//Toast.makeText(this.itemView.context, message, Toast.LENGTH_SHORT).show()
//}


fun ViewGroup.inflate(layoutId: Int): View {
    return from(this.context).inflate(layoutId, this, false)
}

fun ImageView.loadUrl(url: String) {
    Picasso.with(context).load(url).into(this)
}