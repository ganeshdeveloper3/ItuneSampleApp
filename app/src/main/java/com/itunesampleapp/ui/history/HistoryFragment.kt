package com.itunesampleapp.ui.history
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.itunesampleapp.data.ItuneVideoRepo
import com.itunesampleapp.databinding.FragmentVideoBinding
import com.itunesampleapp.db.VideoDatabase
import com.itunesampleapp.ui.video.VideoAdapter
import com.itunesampleapp.ui.video.VideoViewModel
import com.itunesampleapp.ui.video.VideoViewModelProviderFactory

/*
*
* Displaying seen videos list
*
* getting saved data from room db.
*
* */

class HistoryFragment : Fragment() {
    private var _binding: FragmentVideoBinding? = null
    private lateinit var videoAdapter: VideoAdapter
    private lateinit var viewModel: VideoViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

           val ituneVideoRepo = context?.let { VideoDatabase(it) }?.let { ItuneVideoRepo(it) }
           val viewModelProviderFactory = ituneVideoRepo?.let { VideoViewModelProviderFactory(it) }
           viewModel =
               ViewModelProvider(this, viewModelProviderFactory!!).get(VideoViewModel::class.java)
        videoAdapter = VideoAdapter(context)

        _binding = FragmentVideoBinding.inflate(inflater, container, false)
        _binding?.videoRecyclerView?.layoutManager = LinearLayoutManager(context)
        _binding?.videoRecyclerView?.adapter = videoAdapter

        viewModel.getSavedNews().observe(viewLifecycleOwner, Observer { articles ->
            videoAdapter.submitList(articles)
        })
        return _binding?.root

    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}