package com.example.unrd.ui.navigation

interface ToolbarController {

    fun setTitle(title: String)

    fun shouldShowBackArrow(backVisible: Boolean)

    fun hideToolbar()

    fun showToolbar()

}