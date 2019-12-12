package com.example.firstapp

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.firstapp.MediaItem.MediaType.PHOTO
import com.example.firstapp.MediaItem.MediaType.VIDEO
import kotlinx.android.synthetic.main.view_media_item.view.*
import kotlin.properties.Delegates

//class MediaAdapter(val listener: (MediaItem) -> Unit) :
class MediaAdapter(items: List<MediaItem> = emptyList(), val listener: (MediaItem) -> Unit) :
    RecyclerView.Adapter<MediaAdapter.ViewHolder>() {
    //val items: List<MediaItem> by Delegates.observable(emptyList()) { p, old, new -> notifyDataSetChanged() }
    var items: List<MediaItem> by Delegates.observable(items) { _, _, _ -> notifyDataSetChanged() }


    interface OnMediaClickListener {
        fun onClick(mediaItem: MediaItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        //val inflater =
        //    LayoutInflater.from(parent.context).inflate(R.layout.view_media_item, parent, false)
        val inflater = parent.inflate(R.layout.view_media_item)
        return ViewHolder(inflater)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
        //holder.itemView.setOnClickListener { listener.onClick(item) }
        holder.itemView.setOnClickListener { listener(item) }
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
