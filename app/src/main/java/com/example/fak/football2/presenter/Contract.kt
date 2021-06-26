package com.example.fak.football2.presenter

import com.example.fak.football2.model.Event
import com.example.fak.football2.model.Team
import com.example.fak.football2.view.fragment.LastFragment
import com.example.fak.football2.view.fragment.NextFragment

interface Contract {
    interface MatchDetail {
        fun showProgessBar()
        fun hideProgressBar()
        fun getDataTeam()
        fun getEventDetails()
        fun setView()
        fun setBagde()
        fun setFavorite(isFav: Boolean)
    }

    interface MatchDetailPresenter {
        fun loadDataTeam(idAway: String?)
        fun insertFavorite(data: List<Event>)
        fun checkFavorite(matchId: String)
        fun deleteFavorite(matchId: String)
        fun loadEventDetail(matchId: String?)
    }

    interface MatchPresenter {
        fun loadLastMatch()
        fun loadNextMatch()

    }

    interface MatchView {
        fun setView()
    }

}
