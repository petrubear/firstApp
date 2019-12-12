package com.example.firstapp

import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import com.example.firstapp.doAsync as doAsync1

object MediaLibrary {
    private const val thumbBase = "http://lorempixel.com/400/400/cats/"
    /*
    val items = (1..10).map {
        MediaItem(
            "Title $it",
            "${thumbBase}$it",
            if (it % 3 == 0) MediaItem.MediaType.PHOTO else MediaItem.MediaType.VIDEO
        )
    }
    */

    private var data = emptyList<MediaItem>()

    fun dataAsync(callback: (List<MediaItem>) -> Unit) {//callback) {
        var items: List<MediaItem> = emptyList()
        doAsync {
            if (data.isEmpty()) {
                Thread.sleep(2000)
                data = (1..10).map {
                    MediaItem(
                        it,
                        "Title $it",
                        "${thumbBase}$it",
                        if (it % 3 == 0) MediaItem.MediaType.PHOTO else MediaItem.MediaType.VIDEO
                    )
                }
            }
            uiThread {
                callback(data)
            }
        }
    }
}