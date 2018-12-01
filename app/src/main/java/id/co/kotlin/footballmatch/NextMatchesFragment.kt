package id.co.kotlin.footballmatch

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import com.google.gson.Gson
import com.matchschedules.sub.submission2_matchschedules.main.MainPresenter
import com.matchschedules.sub.submission2_matchschedules.model.Match
import id.co.kotlin.footballmatch.Adapter.MainAdapter
import id.co.kotlin.footballmatch.Interface.MainView
import id.co.kotlin.footballmatch.Utils.invisible
import id.co.kotlin.footballmatch.Utils.visible
import kotlinx.android.synthetic.main.activity_main.*

import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.intentFor
import id.co.kotlin.footballmatch.Api.Repository
import org.jetbrains.anko.support.v4.toast

class NextMatchesFragment : Fragment(), MainView {


    private var events: MutableList<Match> = mutableListOf()
    private lateinit var listEvent: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var presenter: MainPresenter
    private lateinit var adapter: MainAdapter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_match, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        listEvent = view.findViewById(R.id.rvFootball)
        progressBar = view.findViewById(R.id.mainProgressBar)
        val layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        adapter = MainAdapter(ctx, events) {
            startActivity(intentFor<MatchDetailActivity>("match" to it))
        }
        listEvent.layoutManager = layoutManager
        listEvent.adapter = adapter
        val request = Repository()
        val gson = Gson()
        presenter = MainPresenter(this, request, gson)
        events.clear()
        presenter.getNextEventList("4332")
   }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }
    override fun showError(message: String) {
        listEvent.visibility = View.GONE
        toast("No Data Found")
    }
    override fun showEventList(data: List<Match>) {
        events.clear()
        events.addAll(data)
        adapter.notifyDataSetChanged()
    }
}
