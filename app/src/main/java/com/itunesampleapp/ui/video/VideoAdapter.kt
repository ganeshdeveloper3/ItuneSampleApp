package com.itunesampleapp.ui.video

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.api.models.Result
import com.devbrackets.android.exomedia.ui.widget.VideoView
import com.itunesampleapp.R
import com.itunesampleapp.databinding.ListItemVideoBinding


class VideoAdapter(val context: Context?) : ListAdapter<Result, VideoAdapter.VideoViewHolder>(VideoDiffCallback()) {
 lateinit var  videoView:VideoView


    inner class VideoViewHolder(itemView:View):RecyclerView.ViewHolder(itemView)
     class VideoDiffCallback:DiffUtil.ItemCallback<Result>() {
         override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
             return oldItem == newItem
         }
         override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {

             return oldItem.toString() == newItem.toString()
         }
     }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        return VideoViewHolder(

            parent.context.getSystemService(LayoutInflater::class.java).inflate(
                R.layout.list_item_video,
                parent,
                false
            )
        )

    }
    private var onItemClickListener: ((Result) -> Unit)? = null

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {

        ListItemVideoBinding.bind(holder.itemView).apply {
             val result = getItem(position)
            textTrackName.text = result.trackName
            videoView = VideoView(context!!)
            videoView.seekTo(1)
            videoThumbNail.setVideoURI(Uri.parse(result.previewUrl))
            root.setOnClickListener {
                onItemClickListener?.let { it(result) }
                videoThumbNail.start()
            }
        }

    }
    fun setOnItemClickListener(listener: (Result) -> Unit) {
        onItemClickListener = listener

    }


}