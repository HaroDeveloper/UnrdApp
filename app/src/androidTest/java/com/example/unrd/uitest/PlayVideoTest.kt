package com.example.unrd.uitest

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.unrd.R
import com.example.unrd.ui.main.MainActivity
import org.junit.Rule
import org.junit.Test

class PlayVideoTest {

    @get:Rule
    var activityRule: ActivityScenarioRule<MainActivity> =
        ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun startPlayingVideo() {
        Thread.sleep(1000)

        onView(withId(R.id.btnMenu))
            .perform(click())

        onView(withId(R.id.videoText)).perform(click())

        onView(withId(R.id.videoListRecycler))
            .perform(
                actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    0,
                    click()
                )
            )

        Thread.sleep(4000)
    }
}