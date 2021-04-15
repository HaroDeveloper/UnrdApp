package com.example.unrd.ui.video

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import com.example.unrd.BaseFragment
import com.example.unrd.R
import com.example.unrd.ui.navigation.ToolbarController
import kotlinx.android.synthetic.main.fragment_video.*

class VideoFragment : BaseFragment() {

    companion object {
        const val VIDEO_URL = "Url"

        fun newInstance(url: String): BaseFragment {
            val args = Bundle().apply {
                putString(VIDEO_URL, url)
            }
            return VideoFragment().apply { arguments = args }
        }
    }

    private var mediaController: MediaController? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_video, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setToolbar()

        configureVideoView()
    }

    private fun setToolbar() {
        (activity as ToolbarController).setTitle(getString(R.string.Video_fragment_title))
        (activity as ToolbarController).shouldShowBackArrow(fragmentManager?.let { it.backStackEntryCount > 0 }
            ?: false)
    }

    private fun configureVideoView() {
        mediaController = MediaController(context)
        mediaController?.setAnchorView(videoView)
        videoView.setMediaController(mediaController)
        arguments?.getString(VIDEO_URL)?.let {
            val uri = Uri.parse(it)
            videoView.setVideoURI(uri)
        }

        videoView.setOnPreparedListener {
            progressBar.visibility = View.GONE
            videoView.start()
        }
    }

}