package com.example.firstapp

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView

//import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), Logger {

    val recyclerView: RecyclerView by lazy { findViewById<RecyclerView>(R.id.recycler) }
    val adapter = MediaAdapter(getMedia()) { (title, _) -> toast(title) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toast("loaded")
        toast("reloaded", Toast.LENGTH_LONG)


        //android kotlin extensions
        //val recycler = findViewById<RecyclerView>(R.id.recycler)

        //recycler.adapter = MediaAdapter(getMedia()) { mediaItem -> toast(mediaItem.title) }
        //recycler.adapter = MediaAdapter(getMedia()) { toast(it.title) }
        //recycler.adapter = MediaAdapter(getMedia()) { (title, url) -> toast(title) }
        //recycler.adapter = MediaAdapter(getMedia()) { (title, _) -> toast(title) }
        /*
        val adapter = MediaAdapter() { (title, _) -> toast(title) }
        adapter.items = getMedia()
        recyclerView.adapter = adapter
        */
        recyclerView.adapter = adapter

        val textView = TextView(this).customApply {
            text = "Hello"
            textSize = 20f
        }
    }

    fun <T> T.customApply(f: T.() -> Unit): T {
        this.f()
        return this
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        /*
        when (item.title) {
            "Videos" -> {
                adapter.items = getMedia().filter { it.type == MediaItem.MediaType.VIDEO }
                return true
            }
            "Photos" -> {
                adapter.items = getMedia().filter { it.type == MediaItem.MediaType.PHOTO }
                return true
            }
            else -> {
                adapter.items = getMedia()
                return true
            }
        }
         */
        adapter.items = getMedia().let { media ->
            when (item.itemId) {
                R.id.filter_all -> media
                R.id.filter_photos -> media.filter { it.type == MediaItem.MediaType.PHOTO }
                R.id.filter_videos -> media.filter { it.type == MediaItem.MediaType.VIDEO }
                else -> emptyList()
            }
        }
        return true
    }
    //    fun toast(message: String) {
//        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
//        d("Hello")
//    }

}

interface Logger {
    val tag: String
        get() = javaClass.simpleName

    fun d(message: String) = Log.d(tag, message)
}

