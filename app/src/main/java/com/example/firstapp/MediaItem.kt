package com.example.firstapp

data class MediaItem(val title: String, var thumbUrl: String, val type: MediaType) {
    enum class MediaType { PHOTO, VIDEO }
}