package com.example.firstapp

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), Logger {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toast("loaded");
        toast("reloaded", Toast.LENGTH_LONG);
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

