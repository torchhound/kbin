package co.whiteboarddynamics.demo.kbin.ui

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import co.whiteboarddynamics.demo.kbin.R
import co.whiteboarddynamics.demo.kbin.models.TrendingPaste
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.logging.Logger

class TrendingAdapter(private val trendingPastes : Observable<Array<TrendingPaste>>) : RecyclerView.Adapter<TrendingAdapter.ViewHolder>() {
    private var currentList : Array<TrendingPaste> = emptyArray()
    val LOG = Logger.getLogger(this.javaClass.name)

    class ViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        trendingPastes.observeOn(AndroidSchedulers.mainThread()).subscribe(
            { it ->
                currentList = it
                LOG.warning(it.toString())
                notifyDataSetChanged()
            }, {
                Toast.makeText(MainActivity.applicationContext(), "Error: $it", Toast.LENGTH_LONG).show()
            }, {
                Toast.makeText(MainActivity.applicationContext(), "Loaded Trending Pastes!", Toast.LENGTH_SHORT).show()
            }
        )

        val textView = LayoutInflater.from(parent.context)
                .inflate(R.layout.trending_text_view, parent, false) as TextView
        return ViewHolder(textView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = currentList[position].paste.pasteTitle
    }

    override fun getItemCount(): Int {
        return currentList.size
    }
}