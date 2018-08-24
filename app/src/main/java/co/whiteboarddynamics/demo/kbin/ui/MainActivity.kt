package co.whiteboarddynamics.demo.kbin.ui

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import co.whiteboarddynamics.demo.kbin.R
import co.whiteboarddynamics.demo.kbin.api.PasteAPI
import co.whiteboarddynamics.demo.kbin.models.Paste
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import java.util.logging.Logger

class MainActivity : AppCompatActivity() {
  private lateinit var allPastesRecyclerView : RecyclerView
  private lateinit var viewAdapter : RecyclerView.Adapter<*>
  private lateinit var viewManager : RecyclerView.LayoutManager
  private lateinit var pasteList : Paste
  val LOG = Logger.getLogger(this.javaClass.name)

  init {
    instance = this
  }

  companion object {
    private var instance: MainActivity? = null
    private lateinit var pasteAPI : PasteAPI

    fun applicationContext() : Context {
      return instance!!.applicationContext
    }

    fun getApi() : PasteAPI {
      return pasteAPI
    }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    supportActionBar?.title = "kbin: Paste.ee client"

    fab.setOnClickListener { _ ->
      NewPasteDialog.show(this)
    }

    main_swipe_refresh_layout.setOnRefreshListener {
      getAllPastes()
    }

    pasteAPI = PasteAPI.create(resources.getString(R.string.PasteApiKey))
    viewManager = LinearLayoutManager(this)
    getAllPastes()
  }

  fun getAllPastes() {
    pasteAPI.getAll().observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(
        { it ->
          pasteList = it
        }, {
      Toast.makeText(MainActivity.applicationContext(), "Error: $it", Toast.LENGTH_LONG).show()
    }, {
      viewAdapter = AllPastesAdapter(pasteList)
      main_swipe_refresh_layout.isRefreshing = false
      Toast.makeText(MainActivity.applicationContext(), "Loaded All Pastes!", Toast.LENGTH_SHORT).show()

      allPastesRecyclerView = all_pastes_recycler_view.apply {
        setHasFixedSize(true)
        layoutManager = viewManager
        adapter = viewAdapter
      }
    }
    )
  }
}
