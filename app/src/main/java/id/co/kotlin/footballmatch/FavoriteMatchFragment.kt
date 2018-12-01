package id.co.kotlin.footballmatch


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import id.co.kotlin.footballmatch.Interface.FavMainView
import id.co.kotlin.footballmatch.helper.FavoriteMatch
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.intentFor
import id.co.kotlin.footballmatch.Adapter.FavAdapter
import id.co.kotlin.footballmatch.Presenter.FavPresenter
import id.co.kotlin.footballmatch.Utils.invisible
import id.co.kotlin.footballmatch.Utils.visible


class FavoriteMatchFragment : Fragment(), FavMainView {
    override fun showEventList(data: List<FavoriteMatch>) {
        events.clear()
        events.addAll(data)
        adapter.notifyDataSetChanged()
    }


    private var events: MutableList<FavoriteMatch> = mutableListOf()
    lateinit var mPresenter: FavPresenter
    private lateinit var listEvent: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var adapter: FavAdapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_match, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        listEvent = view.findViewById(R.id.rvFootball)
        progressBar = view.findViewById(R.id.mainProgressBar)
        val layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        adapter = FavAdapter(ctx, events) {
            startActivity(intentFor<fAVMatchDetailActivity>("match" to it))
        }
        listEvent.layoutManager = layoutManager
        listEvent.adapter = adapter

        mPresenter = FavPresenter(this)
        events.clear()
        mPresenter.getfavoriteMatch(ctx)

    }

    override fun showLoading() {
        progressBar.visible()
        listEvent.visibility = View.INVISIBLE
    }

    override fun hideLoading() {
        progressBar.invisible()
        listEvent.visibility = View.VISIBLE

    }





}
