package com.example.unrd.uitest

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.unrd.R
import com.example.unrd.ui.main.MainActivity
import org.junit.Rule
import org.junit.Test

class OpenUnrdWebsite {
    @get:Rule
    var activityRule: ActivityScenarioRule<MainActivity> =
        ActivityScenarioRule(MainActivity::class.java)
    var positionToScroll = 12

    @Test
    fun openUnrdWeb() {
        Thread.sleep(3000)

        onView(withId(R.id.charactersRecycler))
            .perform(
                scrollToPosition<RecyclerView.ViewHolder>(positionToScroll)
            )

        Thread.sleep(1000)

        onView(withId(R.id.btnMenu))
            .perform(click())

        Thread.sleep(1000)

        onView(withId(R.id.menuTitleBar))
            .perform(click())

        Thread.sleep(3000)

        onView(withId(R.id.btnMenu))
            .perform(click())

    }
}