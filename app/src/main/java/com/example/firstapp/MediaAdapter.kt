package com.example.firstapp

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

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
        val title = itemView.find<TextView>(R.id.media_title)
        //val image = itemView.findViewById<ImageView>(R.id.image)
        val image = find<ImageView>(R.id.image)

        fun bind(item: MediaItem) {
            title.text = item.title
            //Picasso.with(image.context).load(item.thumbUrl).into(image)
            image.loadUrl(item.thumbUrl)
            toast("Hello from media adapter")
        }
    }
}
