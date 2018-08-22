package co.whiteboarddynamics.demo.kbin.ui

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import co.whiteboarddynamics.demo.kbin.R
import co.whiteboarddynamics.demo.kbin.models.Paste
import java.util.logging.Logger

class AllPastesAdapter(private val pastes : Paste) : RecyclerView.Adapter<AllPastesAdapter.ViewHolder>() {
  val LOG = Logger.getLogger(this.javaClass.name)

  class ViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView)

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    LOG.info("AllPastesAdapter")

    val textView = LayoutInflater.from(parent.context)
        .inflate(R.layout.paste_title_text_view, parent, false) as TextView
    return ViewHolder(textView)
  }

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    holder.textView.text = pastes.data[position].description
  }

  override fun getItemCount(): Int {
    return pastes.data.size
  }
}