package com.example.unrd.ui.video

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.unrd.BaseFragment
import com.example.unrd.R
import com.example.unrd.data.model.VideoWrapper
import com.example.unrd.ui.home.HomeViewModel
import com.example.unrd.ui.navigation.ToolbarController
import kotlinx.android.synthetic.main.fragment_video_list.*
import org.koin.android.viewmodel.ext.android.sharedViewModel

class VideoListFragment : BaseFragment() {
    private val viewModel: HomeViewModel by sharedViewModel()
    private var videoList: MutableList<VideoWrapper>? = mutableListOf()

    companion object {
        fun newInstance(): BaseFragment {
            return VideoListFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_video_list, container, false)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        videoList?.clear()
        videoListRecycler.adapter = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setToolbar()
        setObservers()
    }

    private fun setToolbar() {
        (activity as ToolbarController).setTitle(getString(R.string.videoListFragmentTitle))
        (activity as ToolbarController).shouldShowBackArrow(fragmentManager?.let { it.backStackEntryCount > 0 }
            ?: false)
    }

    private fun setObservers() {
        viewModel.story.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            videoList?.add(
                VideoWrapper(
                    it.result.videoList[0].videoUrl,
                    getString(R.string.intro_video)
                )
            )
            videoList?.add(
                VideoWrapper(
                    it.result.previewMedia[0].videoUrl,
                    getString(R.string.preview_video)
                )
            )

            videoList?.let { it1 -> setAdapter(it1) }
        })
    }

    private fun setAdapter(videoList: MutableList<VideoWrapper>) {
        val adapter = VideoAdapter(videoList)
        videoListRecycler.adapter = adapter

        adapter.listener = object : VideoRecyclerListener {
            override fun videoItemClicked(videoUrl: String) {
                navigate(VideoFragment.newInstance(videoUrl))
            }
        }
    }
}