package com.itunesampleapp.ui.video
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.itunesampleapp.data.ItuneVideoRepo
import com.itunesampleapp.databinding.FragmentVideoBinding
import com.itunesampleapp.db.VideoDatabase

/*
* Displying list of videos from itune api
*
*
*
* */

class VideoFragment : Fragment() {
    private var _binding: FragmentVideoBinding? = null
    private lateinit var viewModel: VideoViewModel
    private lateinit var videoAdapter: VideoAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        _binding = FragmentVideoBinding.inflate(inflater, container, false)
        _binding?.videoRecyclerView?.layoutManager = LinearLayoutManager(context)
        videoAdapter = VideoAdapter(context)
        _binding?.videoRecyclerView?.adapter = videoAdapter
        val ituneVideoRepo = context?.let { VideoDatabase(it) }?.let { ItuneVideoRepo(it) }
        val viewModelProviderFactory = ituneVideoRepo?.let { VideoViewModelProviderFactory(it) }
        viewModel =
            ViewModelProvider(this, viewModelProviderFactory!!).get(VideoViewModel::class.java)

        viewModel.fetchVideo()
        viewModel.video.observe({ lifecycle }) {
            videoAdapter.submitList(it)
        }
        videoAdapter.setOnItemClickListener {

            viewModel.saveArticle(it)
            Toast.makeText(context, "TITLE is now saved in database", Toast.LENGTH_LONG).show()
        }
        return _binding?.root

    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}