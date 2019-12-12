package com.example.firstapp

object MediaLibrary {
    private const val thumbBase = "http://lorempixel.com/400/400/cats/"
    val items = (1..10).map {
        MediaItem(
            "Title $it",
            "${thumbBase}$it",
            if (it % 3 == 0) MediaItem.MediaType.PHOTO else MediaItem.MediaType.VIDEO
        )
    }
}