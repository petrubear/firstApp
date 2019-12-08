package com.example.firstapp

private val thumbBase = "http://lorempixel.com/400/400/cats/"
fun getMedia() = listOf(
    MediaItem("Title 1", "${thumbBase}1", MediaItem.MediaType.PHOTO),
    MediaItem("Title 2", "${thumbBase}2", MediaItem.MediaType.PHOTO),
    MediaItem("Title 3", "${thumbBase}3", MediaItem.MediaType.VIDEO),
    MediaItem("Title 4", "${thumbBase}4", MediaItem.MediaType.PHOTO),
    MediaItem("Title 5", "${thumbBase}5", MediaItem.MediaType.PHOTO),
    MediaItem("Title 6", "${thumbBase}6", MediaItem.MediaType.VIDEO),
    MediaItem("Title 7", "${thumbBase}7", MediaItem.MediaType.VIDEO),
    MediaItem("Title 8", "${thumbBase}8", MediaItem.MediaType.PHOTO),
    MediaItem("Title 9", "${thumbBase}9", MediaItem.MediaType.PHOTO),
    MediaItem("Title 10", "${thumbBase}10", MediaItem.MediaType.VIDEO)
)