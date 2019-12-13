package com.example.firstapp

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.*
import org.jetbrains.anko.startActivity
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.resume
import kotlin.system.measureTimeMillis

//import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), Logger, CoroutineScope {

    val recyclerView: RecyclerView by lazy { findViewById<RecyclerView>(R.id.recycler) }
    //val adapter = MediaAdapter() { (title, _) -> toast(title) }
    val adapter = MediaAdapter { navigateToDetail(it) }

    private lateinit var job: Job

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    private fun navigateToDetail(item: MediaItem) {
        startActivity<DetailActivity>(Pair(DetailActivity.ID, item.id))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        job = Job()
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
        MediaLibrary.dataAsync { adapter.items = it }

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
        /*adapter.items = getMedia().let { media ->
            when (item.itemId) {
                R.id.filter_all -> media
                R.id.filter_photos -> media.filter { it.type == MediaItem.MediaType.PHOTO }
                R.id.filter_videos -> media.filter { it.type == MediaItem.MediaType.VIDEO }
                else -> emptyList()
            }
        }*/

        /*
        MediaLibrary.dataAsync { media ->
            adapter.items =
                when (item.itemId) {
                    R.id.filter_all -> media
                    R.id.filter_photos -> media.filter { it.type == MediaItem.MediaType.PHOTO }
                    R.id.filter_videos -> media.filter { it.type == MediaItem.MediaType.VIDEO }
                    else -> emptyList()
                }
        }
         */


        /*val filter: Filter =
            when (item.itemId) {
                R.id.filter_all -> Filter.AllFilter()
                R.id.filter_photos -> Filter.PhotoFilter()
                R.id.filter_videos -> Filter.VideoFilter()
                else -> Filter.EmptyFilter()
            }

         */

        val filter: Filter =
            when (item.itemId) {
                R.id.filter_all -> Filter.None
                R.id.filter_photos -> Filter.ByType(MediaItem.MediaType.PHOTO)
                R.id.filter_videos -> Filter.ByType(MediaItem.MediaType.VIDEO)
                else -> Filter.None
            }


        val timeMillis = measureTimeMillis {
            loadFilteredData(filter)
        }

        print("===> time: $timeMillis")
        return true
    }

    private fun loadFilteredData(filter: Filter) {
        /*
        MediaLibrary.dataAsync { media ->
            adapter.items =
                when (filter) {
                    is Filter.AllFilter -> media
                    is Filter.PhotoFilter -> media.filter { it.type == MediaItem.MediaType.PHOTO }
                    is Filter.VideoFilter -> media.filter { it.type == MediaItem.MediaType.VIDEO }
                    else -> emptyList()
                }
        }
         */
        //progress.show()
        /*
        MediaLibrary.dataAsync("cats") { media1 ->
            MediaLibrary.dataAsync("cars") { media2 ->
                updateDate(filter, media1 + media2)
            }
        }*/
        //GlobalScope.launch(Dispatchers.Main) {
        launch {
            //val media1 = withContext(Dispatchers.IO) { MediaLibrary.dataSync("cats") }
            //val media2 = withContext(Dispatchers.IO) { MediaLibrary.dataSync("cars") }

            //val media1 = getData("cats")
            //val media2 = getData("cars ")
            //updateDate(filter, media1 + media2)

            val media1 = async(Dispatchers.IO) { MediaLibrary.dataSync("cats") }
            val media2 = async(Dispatchers.IO) { MediaLibrary.dataSync("cars") }
            val media3 = useAsync()
            updateDate(filter, media1.await() + media2.await())

        }
    }

    private suspend fun getData(type: String): List<MediaItem> = withContext(Dispatchers.IO) {
        MediaLibrary.dataSync(type)
    }


    private suspend fun useAsync(): List<MediaItem> = suspendCancellableCoroutine{continuation ->
        MediaLibrary.dataAsync { media ->
            continuation.resume(media)
        }
    }

    private fun updateDate(
        filter: Filter,
        media: List<MediaItem>
    ) {
        adapter.items =
            when (filter) {
                is Filter.None -> media
                is Filter.ByType -> media.filter { it.type == filter.type }
            }
    }


    //    fun toast(message: String) {
//        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
//        d("Hello")
//    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }

}

interface Logger {
    val tag: String
        get() = javaClass.simpleName

    fun d(message: String) = Log.d(tag, message)
}

