package com.example.fak.football2.presenter

import android.annotation.SuppressLint
import com.example.fak.football2.helper.DatabaseHelper
import com.example.fak.football2.model.*
import com.example.fak.football2.network.ApiRequest
import com.example.fak.football2.network.ConfigRetro
import com.example.fak.football2.util.FavoriteConstan
import com.example.fak.football2.util.MainScheduler
import com.example.fak.football2.view.DetailMatchActivity
import org.jetbrains.anko.db.*

class MatchDetailPresenter(val view: DetailMatchActivity, val scheduler: MainScheduler) :
    Contract.MatchDetailPresenter {

    val teams = arrayListOf<Team>()
    val event = arrayListOf<Event>()
    var fav: Boolean = false
    lateinit var db: DatabaseHelper
    override fun insertFavorite(data: List<Event>) {
        db = DatabaseHelper(view)
        db.use {
            insert(
                FavoriteConstan.TABLE_MATCH,
                FavoriteConstan.MATCH_ID to data[0].idEvent,
                FavoriteConstan.MATCH_DATE to data[0].dateEventLocal,
                FavoriteConstan.HOME_ID to data[0].idHomeTeam,
                FavoriteConstan.AWAY_ID to data[0].idAwayTeam,
                FavoriteConstan.HOME_NAME to data[0].strHomeTeam,
                FavoriteConstan.AWAY_NAME to data[0].strAwayTeam,
                FavoriteConstan.HOME_GOAL to data[0].intHomeScore,
                FavoriteConstan.AWAY_GOAL to data[0].intAwayScore,
                FavoriteConstan.HOME_SHOT to data[0].intHomeShots,
                FavoriteConstan.AWAY_SHOT to data[0].intAwayShots,
                FavoriteConstan.HOME_DEFENDER to data[0].strHomeLineupGoalkeeper,
                FavoriteConstan.AWAY_DEFENDER to data[0].strAwayLineupGoalkeeper,
                FavoriteConstan.HOME_MIDFIELD to data[0].strHomeLineupMidfield,
                FavoriteConstan.AWAY_MIDFIELD to data[0].strAwayLineupMidfield,
                FavoriteConstan.HOME_FORWARD to data[0].strHomeLineupForward,
                FavoriteConstan.AWAY_FORWARD to data[0].strAwayLineupForward,
                FavoriteConstan.HOME_SUBTITUTES to data[0].strHomeLineupSubstitutes,
                FavoriteConstan.AWAY_SUBTITUTES to data[0].strAwayLineupSubstitutes
            )

        }
        view.setFavorite(true)
    }

    override fun checkFavorite(matchId: String) {
        db = DatabaseHelper(view)
        db.use {
            val data = select(FavoriteConstan.TABLE_MATCH).whereArgs("(MATCH_ID = {id})", "id" to matchId)
            val favorites = data.parseList(classParser<FavoriteData>())
            if (!favorites.isEmpty()) fav = true

            view.setFavorite(fav)

        }
    }

    override fun deleteFavorite(matchId: String) {
        db = DatabaseHelper(view)
        db.use {
            delete(FavoriteConstan.TABLE_MATCH, "(MATCH_ID = {id})", "id" to matchId)
        }
        view.setFavorite(false)
    }

    @SuppressLint("CheckResult")
    override fun loadEventDetail(matchId: String?) {

        val service: ApiRequest = ConfigRetro.config()
            .create(ApiRequest::class.java)
        service.eventDetails(matchId).subscribeOn(scheduler.io())
            .observeOn(scheduler.ui())
            .subscribe { it ->
                event.clear()
                event.addAll(it.events!!)
                view.setView()
            }
    }

    override fun loadDataTeam(id: String?) {
        val service: ApiRequest = ConfigRetro.config()
            .create(ApiRequest::class.java)
        service.teamDetails(id).subscribeOn(scheduler.io())
            .observeOn(scheduler.ui())
            .subscribe { it ->
                teams.clear()
                teams.addAll(it.teams!!)
                view.setBagde()
            }
    }


}