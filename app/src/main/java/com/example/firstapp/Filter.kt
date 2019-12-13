package com.example.firstapp

sealed class Filter {
    object None : Filter()
    class ByType(val type: MediaItem.MediaType) : Filter()
}