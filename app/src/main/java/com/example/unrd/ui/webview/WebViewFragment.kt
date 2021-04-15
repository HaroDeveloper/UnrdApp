package com.example.unrd.ui.webview

import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.unrd.BaseFragment
import com.example.unrd.R
import com.example.unrd.ui.navigation.ToolbarController
import kotlinx.android.synthetic.main.fragment_webview.*

class WebViewFragment : BaseFragment() {

    companion object {
        const val WEB_URI = "Uri"

        fun newInstance(url: String): BaseFragment {
            val args = Bundle().apply {
                putString(WEB_URI, url)
            }
            return WebViewFragment().apply { arguments = args }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_webview, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setToolbar()

        webView.apply {
            with(settings) {
                javaScriptEnabled = true
                domStorageEnabled = true
                loadWithOverviewMode = false
                useWideViewPort = true
                setSupportZoom(true)
                builtInZoomControls = false
                cacheMode = WebSettings.LOAD_NO_CACHE
            }
            setPadding(0, 0, 0, 0)
            setInitialScale(1)

            arguments?.getString(WEB_URI)?.let {
                loadUrl(it)

            }

            if (Build.VERSION.SDK_INT >= 21)
                setLayerType(View.LAYER_TYPE_HARDWARE, null) // enable hardware acceleration
            else
                setLayerType(View.LAYER_TYPE_SOFTWARE, null) // disable hardware acceleration
        }

        webView.webViewClient = object : WebViewClient() {
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                progressBar.visibility = View.GONE
            }

        }
    }

    private fun setToolbar() {
        (activity as ToolbarController).setTitle(getString(R.string.webview_fragment_title))
        (activity as ToolbarController).shouldShowBackArrow(fragmentManager?.let { it.backStackEntryCount > 0 }
            ?: false)
    }
}