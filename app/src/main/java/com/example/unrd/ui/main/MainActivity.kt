package com.example.unrd.ui.main

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.example.unrd.R
import com.example.unrd.ui.home.HomeFragment
import com.example.unrd.ui.menu.MenuInterface
import com.example.unrd.ui.navigation.NavigationController
import com.example.unrd.ui.navigation.NavigationManager
import com.example.unrd.ui.navigation.ToolbarController
import com.example.unrd.ui.video.VideoListFragment
import com.example.unrd.ui.webview.WebViewFragment
import com.example.unrd.utils.Constants
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity(), NavigationController, ToolbarController, MenuInterface {
    private val viewModel: MainViewModel by inject()
    private lateinit var navigationManager: NavigationManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        setNavigation()
        setClickListeners()
        openHomeFragment()
        menu.menuClickListener = this
    }

    private fun setClickListeners() {
        btnMenu.setOnClickListener {
            if (!goBack())
                toggleDrawer()
        }
    }

    private fun openHomeFragment() {
        navigate(HomeFragment())
    }

    override fun onBackPressed() {
        if (navigationManager.canGoBack())
            goBack()
        else
            super.onBackPressed()
    }

    private fun setNavigation() {
        navigationManager = NavigationManager(supportFragmentManager)
    }

    override fun navigate(fragment: Fragment) {
        navigationManager.navigate(fragment)
    }

    override fun goBack(): Boolean {
        return navigationManager.goBack()
    }

    override fun canGoBack(): Boolean {
        return navigationManager.canGoBack()
    }

    override fun setTitle(title: String) {
        toolbarTitle.text = title
    }

    override fun shouldShowBackArrow(backVisible: Boolean) {
        if (backVisible) {
            btnMenu.setBackgroundResource(R.drawable.ic_baseline_arrow_back_ios_24)
        } else {
            btnMenu.setBackgroundResource(R.drawable.ic_menu)
        }
    }

    override fun hideToolbar() {
        toolbar.visibility = View.GONE
    }

    override fun showToolbar() {
        toolbar.visibility = View.VISIBLE
    }

    override fun videoListClicked() {
        navigate(VideoListFragment.newInstance())
        toggleDrawer()
    }

    override fun unrdClicked() {
        navigate(WebViewFragment.newInstance(Constants.UNRD_WEBSITE_URL))
        toggleDrawer()
    }

    private fun toggleDrawer() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            drawerLayout.openDrawer(GravityCompat.START)
        }
    }
}