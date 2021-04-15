package com.example.unrd.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.unrd.BaseFragment
import com.example.unrd.R
import com.example.unrd.data.model.Characters
import com.example.unrd.data.model.Story
import com.example.unrd.ui.navigation.ToolbarController
import com.example.unrd.ui.webview.WebViewFragment
import com.example.unrd.utils.Constants
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.android.viewmodel.ext.android.sharedViewModel

class HomeFragment : BaseFragment() {
    private val viewModel: HomeViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setToolbar()
        setClickListeners()
        setObservers()
        viewModel.getStory()

    }

    private fun setClickListeners() {
        unrdBar.setOnClickListener {
            navigate(WebViewFragment.newInstance(Constants.UNRD_WEBSITE_URL))
        }
    }

    private fun setObservers() {
        viewModel.story.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            if (it != null)
                setData(it)
        })

        viewModel.status.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            progressBar.visibility = View.GONE
            if (it)
                errorTextView.visibility = View.GONE
            else
                errorTextView.visibility = View.VISIBLE
        })
    }

    private fun setData(story: Story) {
        title.text = story.result.shortSummary
        summary.text = story.result.fullSummary
        Glide.with(this)
            .load(story.result.listImage[0].titlePageResourceUri)
            .into(titleImage)

        charactersTitle.text = resources.getString(R.string.characters)

        setBottomInfo(story)
        unrdBar.visibility = View.VISIBLE
        setCharactersRecycler(story.result.characters)
    }

    private fun setBottomInfo(story: Story) {
        durationLabel.text = resources.getString(R.string.duration)
        minimumAgeLabel.text = resources.getString(R.string.minAge)
        duration.text = story.result.duration
        minimumAge.text = story.result.minAge
    }

    private fun setCharactersRecycler(characters: List<Characters>) {
        charactersRecycler.adapter = CharacterAdapter(characters)
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        charactersRecycler.layoutManager = layoutManager
    }

    private fun setToolbar() {
        (activity as ToolbarController).setTitle(getString(R.string.home_fragment_title))
        (activity as ToolbarController).shouldShowBackArrow(fragmentManager?.let { it.backStackEntryCount > 0 }
            ?: false)
    }
}