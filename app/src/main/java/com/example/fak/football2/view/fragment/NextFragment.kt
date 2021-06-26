package com.example.fak.football2.view.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fak.football2.view.adapter.AdapterRecyclerNext
import com.example.fak.football2.R
import com.example.fak.football2.model.Event
import com.example.fak.football2.presenter.Contract
import com.example.fak.football2.presenter.MatchPresenter
import com.example.fak.football2.util.Scheduler
import com.example.fak.football2.view.DetailMatchActivity
import org.jetbrains.anko.support.v4.startActivity

class NextFragment : Fragment(), Contract.MatchView {
    lateinit var rec: RecyclerView
    val event = arrayListOf<Event>()
    private lateinit var presenter: MatchPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_next, container, false)
        rec = v.findViewById<RecyclerView>(R.id.next_recycler)
        presenter = MatchPresenter(Scheduler(), this)
        presenter.loadNextMatch()

        return v
    }

    override fun setView() {
        event.addAll(presenter.event)
        rec.layoutManager = LinearLayoutManager(activity)
        rec.adapter = AdapterRecyclerNext(this.activity!!, event) {

            startActivity<DetailMatchActivity>("detail" to "${it.idEvent}")

        }
    }
}
