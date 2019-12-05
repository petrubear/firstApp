package com.example.firstapp

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.firstapp.MediaItem.MediaType.PHOTO
import com.example.firstapp.MediaItem.MediaType.VIDEO
import kotlinx.android.synthetic.main.view_media_item.view.*

class MediaAdapter(val items: List<MediaItem>) : RecyclerView.Adapter<MediaAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        //val inflater =
        //    LayoutInflater.from(parent.context).inflate(R.layout.view_media_item, parent, false)
        val inflater = parent.inflate(R.layout.view_media_item)
        return ViewHolder(inflater)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        //val title = itemView.findViewById<TextView>(R.id.media_title)
        //val title = itemView.find<TextView>(R.id.media_title)
        //val title = itemView.media_title

        //val image = itemView.findViewById<ImageView>(R.id.image)
        //val image = find<ImageView>(R.id.media_thumb)
        //val image = itemView.media_thumb

        //val videoIndicator = find<ImageView>(R.id.media_video_indicator)
        //val videoIndicator = itemView.media_video_indicator


        fun bind(item: MediaItem) {
            //title.text = item.title
            //Picasso.with(image.context).load(item.thumbUrl).into(image)
            //image.loadUrl(item.thumbUrl)

            //videoIndicator.visibility = when (item.type) {
            //    PHOTO -> View.GONE
            //    VIDEO -> View.VISIBLE
            //}

            with(itemView) {
                media_title.text = item.title
                media_thumb.loadUrl(item.thumbUrl)
                media_video_indicator.visibility = when (item.type) {
                    PHOTO -> View.GONE
                    VIDEO -> View.VISIBLE
                }
            }
            toast("Hello from media adapter")
        }
    }
}
