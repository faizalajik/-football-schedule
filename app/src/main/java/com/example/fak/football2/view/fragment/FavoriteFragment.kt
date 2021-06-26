package com.example.fak.football2.view.fragment


import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.fak.football2.R
import com.example.fak.football2.helper.DatabaseHelper
import com.example.fak.football2.model.FavoriteData
import com.example.fak.football2.util.FavoriteConstan
import com.example.fak.football2.view.DetailMatchActivity
import com.example.fak.football2.view.adapter.AdapterFavorite
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.support.v4.startActivity

class FavoriteFragment : Fragment() {

    val favorites = arrayListOf<FavoriteData>()
    private lateinit var db: DatabaseHelper
    private lateinit var rec: RecyclerView
    private lateinit var swipe: SwipeRefreshLayout
    private lateinit var snackbar: Snackbar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v: View
        v = inflater.inflate(R.layout.fragment_favorite, container, false)
        rec = v.findViewById(R.id.fav_recycler) as RecyclerView
        swipe = v.findViewById(R.id.swipe) as SwipeRefreshLayout
        db = DatabaseHelper(this.activity!!)
        showFavorite()
        rec.layoutManager = LinearLayoutManager(activity)
        rec.adapter = AdapterFavorite(this.activity!!, favorites) {
            startActivity<DetailMatchActivity>("detail" to it)
        }
        swipe.setOnRefreshListener {
            showFavorite()
        }

        return v
    }

    fun showFavorite() {
        favorites.clear()
        db.use {
            val data = select(FavoriteConstan.TABLE_MATCH)
            val fav = data.parseList(classParser<FavoriteData>())
            favorites.addAll(fav)
            swipe.isRefreshing = false
            rec.adapter?.notifyDataSetChanged()
        }

    }
}
