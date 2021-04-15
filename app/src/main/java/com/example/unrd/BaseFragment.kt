package com.example.unrd

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.unrd.ui.navigation.NavigationController

abstract class BaseFragment : Fragment(), NavigationController {

    private var navigationController: NavigationController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        navigationController = context as? NavigationController
    }

    override fun onDetach() {
        super.onDetach()
        navigationController = null
    }

    override fun goBack(): Boolean = navigationController?.goBack() ?: false

    override fun navigate(fragment: Fragment) {
        navigationController?.navigate(fragment)
    }

    override fun canGoBack(): Boolean = navigationController?.canGoBack() ?: false
}