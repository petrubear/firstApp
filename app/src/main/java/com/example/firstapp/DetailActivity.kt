package com.example.firstapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    companion object {
        val ID = "detailactivity.id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val id = intent.getIntExtra(ID, -1)

        MediaLibrary.dataAsync { media ->
            // val selectedItem = media.find { it.id == id }
            // supportActionBar?.title = selectedItem?.title
            media.find { it.id == id }?.let { (_, title, thumbUrl, type) ->
                supportActionBar?.title = title
                detail_thumb.loadUrl(thumbUrl)
                detail_video_indicator.visibility =
                    if (type == MediaItem.MediaType.VIDEO) View.VISIBLE else View.GONE
            }
        }
    }
}
