package co.whiteboarddynamics.demo.kbin.ui

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import co.whiteboarddynamics.demo.kbin.BuildConfig
import co.whiteboarddynamics.demo.kbin.R
import co.whiteboarddynamics.demo.kbin.api.PastebinAPI
import co.whiteboarddynamics.demo.kbin.models.TrendingPaste
import io.reactivex.Observable
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var trendingRecyclerView : RecyclerView
    private lateinit var viewAdapter : RecyclerView.Adapter<*>
    private lateinit var viewManager : RecyclerView.LayoutManager
    private val pastebinAPI : PastebinAPI = PastebinAPI.create()

    init {
        instance = this
    }

    companion object {
        private var instance: MainActivity? = null

        fun applicationContext() : Context {
            return instance!!.applicationContext
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewManager = LinearLayoutManager(this)
        val trendingPastes : Observable<Array<TrendingPaste>> = pastebinAPI.postTrending(BuildConfig.PastebinApiKey)
        viewAdapter = TrendingAdapter(trendingPastes)

        trendingRecyclerView = trending_recycler_view.apply {
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }
}
