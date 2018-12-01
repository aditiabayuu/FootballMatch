package id.co.kotlin.footballmatch

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.widget.ProgressBar
import com.matchschedules.sub.submission2_matchschedules.main.MainPresenter
import com.matchschedules.sub.submission2_matchschedules.model.Match
import id.co.kotlin.footballmatch.Adapter.MainAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottom_navigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.lastMatch -> {
                    loadMatch(savedInstanceState)
                }
                R.id.nextMatch -> {
                    loadUpcomingMatch(savedInstanceState)
                }
                R.id.Favorite -> {
                    LoadFavMatch(savedInstanceState)
                    //loadFavoritesMatch(savedInstanceState)
                }
            }
            true
        }
        bottom_navigation.selectedItemId = R.id.lastMatch
    }

    private fun loadMatch(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_container, LastMatchesFragment(), LastMatchesFragment::class.java.simpleName)
                    .commit()
        }
    }

    private fun loadUpcomingMatch(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_container, NextMatchesFragment(), NextMatchesFragment::class.java.simpleName)
                    .commit()
        }
    }

    private fun LoadFavMatch(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_container, FavoriteMatchFragment(), FavoriteMatchFragment::class.java.simpleName)
                    .commit()
        }
    }
}
