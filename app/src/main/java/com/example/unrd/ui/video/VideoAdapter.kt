package com.example.unrd.ui.video

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.unrd.R
import com.example.unrd.data.model.VideoWrapper
import kotlinx.android.synthetic.main.video_recycler_item.view.*

class VideoAdapter(private var videoList: MutableList<VideoWrapper>) :
    RecyclerView.Adapter<VideoAdapter.ViewHolder>() {

    private var context: Context? = null
    var listener: VideoRecyclerListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.video_recycler_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return videoList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val current = videoList[position]

        holder.itemView.videoItem.text = current.videoTitle
        holder.videoItem.setOnClickListener {
            listener?.videoItemClicked(current.videoUrl)
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var videoItem = itemView.videoItem
    }
}


