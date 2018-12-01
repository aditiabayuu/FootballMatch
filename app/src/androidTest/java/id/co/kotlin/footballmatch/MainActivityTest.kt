package id.co.kotlin.footballmatch

import android.app.PendingIntent.getActivity
import android.support.test.espresso.Espresso
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.Espresso.pressBack
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import id.co.kotlin.footballmatch.R.id.*
import org.hamcrest.CoreMatchers.endsWith
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.RootMatchers.withDecorView
import android.support.test.espresso.Espresso.onView

import  android.support.test.espresso.assertion.ViewAssertions.matches;
import android.support.test.espresso.contrib.RecyclerViewActions
import  android.support.test.espresso.matcher.RootMatchers.withDecorView;
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import android.support.test.espresso.matcher.ViewMatchers.withText;
import android.support.v7.widget.RecyclerView

import org.hamcrest.Matchers.not;
import android.support.test.espresso.IdlingRegistry
import org.junit.After
import org.junit.Before




/**
 * Created by root on 3/1/18.
 */
@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    @Rule
    @JvmField
    var activityRule = ActivityTestRule(MainActivity::class.java)
    var idlingResource: mainidlingsource? = null
    @Before
    fun registerIdlingResource() {
        idlingResource = mainidlingsource(3000) // mActivityRule.getActivity().getIdlingResource();
        Espresso.registerIdlingResources(idlingResource)}

    // Unregister your Idling Resource so it can be garbage collected and does not leak any memory
    @After
    fun unregisterIdlingResource() {
        IdlingRegistry.getInstance().unregister(idlingResource)
    }
    @Test
    fun testRecyclerViewBehaviour() {
         onView(withId(rvFootball))
                 .check(matches((isDisplayed())))
        onView(withId(rvFootball)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(10))
        onView(withId(rvFootball)).perform(
        RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(10, click()))


    }

    @Test
    fun testAddMatchFavorite() {
        onView(withText("Cagliari")).perform(click())
        onView(withId(favorites)).perform(click())
        onView(withText("Event added to favorite"))
                .inRoot(withDecorView(not(activityRule.getActivity().getWindow().getDecorView())))
                .check(matches(isDisplayed()));
        pressBack()
        onView(withId(Favorite)).perform(click())
        onView(withId(Favorite))
                .check(matches(isDisplayed()))
        onView(withText("Cagliari")).perform(click())
        pressBack()
    }
    @Test
    fun testremoveMatchFavorite() {
        onView(withId(Favorite)).perform(click())
        onView(withId(Favorite))
                .check(matches(isDisplayed()))
        onView(withText("Cagliari")).perform(click())
        onView(withId(favorites)).perform(click())
        onView(withText("Event removed favorite"))
                .inRoot(withDecorView(not(activityRule.getActivity().getWindow().getDecorView())))
                .check(matches(isDisplayed()));
        pressBack()
    }
}