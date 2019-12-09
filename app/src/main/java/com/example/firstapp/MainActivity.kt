package com.example.firstapp

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), Logger {

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
        recycler.adapter = MediaAdapter(getMedia()) { (title, _) -> toast(title) }

        val textView = TextView(this).customApply {
            text = "Hello"
            textSize = 20f
        }
    }

    fun <T> T.customApply (f: T.() -> Unit):T {
        this.f()
        return this
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

