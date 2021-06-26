package com.example.fak.football2

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.Espresso.pressBack
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import com.example.fak.football2.R.id.*
import com.example.fak.football2.view.MainActivity
import org.junit.Rule
import org.junit.Test

import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    @Rule
    @JvmField
    var activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testUI() {
        onView(withId(con)).check(matches(isDisplayed()))
        Thread.sleep(500)
        onView(withId(pb)).check(matches(isDisplayed()))
        Thread.sleep(500)
        onView(withId(last_recycler)).check(matches(isDisplayed()))
        Thread.sleep(500)
        onView(withId(last_recycler)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(10))
        Thread.sleep(500)
        onView(withId(R.id.last_recycler)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(3,click()))
        Thread.sleep(2000)
        pressBack()
        onView(withId(R.id.bottom_nav)).check(matches(isDisplayed()))
        Thread.sleep(500)
        onView(withId(bottom_nav)).perform(click())
        Thread.sleep(500)
        onView(withId(pb)).check(matches(isDisplayed()))
        Thread.sleep(500)
        onView(withId(next_recycler)).check(matches(isDisplayed()))
        Thread.sleep(1000)
        onView(withId(next_recycler)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(10))
        Thread.sleep(500)
    }

}