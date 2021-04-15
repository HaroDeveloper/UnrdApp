package com.example.unrd.ui.navigation

import androidx.fragment.app.Fragment

interface NavigationController {

    fun navigate(fragment: Fragment)

    fun goBack(): Boolean

    fun canGoBack(): Boolean

}