package com.example.unrd.ui.navigation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.unrd.R

class NavigationManager(var fragmentManager: FragmentManager) : NavigationController {

    override fun navigate(fragment: Fragment) {
        fragmentManager.executePendingTransactions()
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)

        val lastFragment = fragmentManager.findFragmentById(R.id.fragment_container)
        if (lastFragment != null) {
            transaction.addToBackStack(fragment.tag)
        }
        transaction.commitAllowingStateLoss()
    }

    override fun goBack(): Boolean {
        return fragmentManager.popBackStackImmediate()
    }

    override fun canGoBack(): Boolean {
        return fragmentManager.backStackEntryCount > 1
    }

}