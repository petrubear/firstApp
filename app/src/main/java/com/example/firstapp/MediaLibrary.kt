package com.example.firstapp

import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

object MediaLibrary {
    private const val thumbBase = "http://lorempixel.com/400/400/"
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

    fun dataAsync(type: String = "cats", callback: (List<MediaItem>) -> Unit) {//callback) {
        var items: List<MediaItem> = emptyList()
        doAsync {
            if (data.isEmpty()) {
                data = dataSync(type)
                /*Thread.sleep(2000)
                data = (1..10).map {
                    MediaItem(
                        it,
                        "Title $it",
                        "${thumbBase}$type/$it",
                        if (it % 3 == 0) MediaItem.MediaType.PHOTO else MediaItem.MediaType.VIDEO
                    )
                }*/
            }
            uiThread {
                callback(data)
            }
        }
    }

    fun dataSync(type: String): List<MediaItem> {
        Thread.sleep(2000)
        return (1..10).map {
            MediaItem(
                it,
                "Title $it",
                "${thumbBase}$type/$it",
                if (it % 3 == 0) MediaItem.MediaType.PHOTO else MediaItem.MediaType.VIDEO
            )
        }
    }
}