package id.co.kotlin.footballmatch

import android.support.test.espresso.IdlingResource

class mainidlingsource (private val waitingTime: Long) : IdlingResource {
    private val startTime: Long
    private var resourceCallback: IdlingResource.ResourceCallback? = null

    init {
        this.startTime = System.currentTimeMillis()
    }

    override fun getName(): String {
        return MainActivity::class.java.name + ":" + waitingTime
    }

    override fun isIdleNow(): Boolean {
        val elapsed = System.currentTimeMillis() - startTime
        val idle = elapsed >= waitingTime
        if (idle) {
            resourceCallback!!.onTransitionToIdle()
        }
        return idle
    }

    override fun registerIdleTransitionCallback(resourceCallback: IdlingResource.ResourceCallback) {
        this.resourceCallback = resourceCallback
    }
}