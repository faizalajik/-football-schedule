package com.example.fak.football2.view


import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import com.example.fak.football2.R
import com.example.fak.football2.model.Team
import com.example.fak.football2.presenter.Contract
import com.example.fak.football2.presenter.MatchDetailPresenter
import com.example.fak.football2.util.Scheduler
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*
import org.jetbrains.anko.contentView
import java.text.SimpleDateFormat
import java.util.*

class DetailMatchActivity : AppCompatActivity(), Contract.MatchDetail {
    private lateinit var itemFav: MenuItem
    private var fav: Boolean = false
    var teams = arrayListOf<Team>()
    lateinit var eventId: String
    private lateinit var presenter: MatchDetailPresenter
    private lateinit var v: View
    override fun showProgessBar() {
        pb.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        pb.visibility = View.GONE
    }

    override fun getDataTeam() {

        presenter.loadDataTeam(presenter.event[0].idAwayTeam)
        presenter.loadDataTeam(presenter.event[0].idHomeTeam)
    }

    override fun getEventDetails() {

        presenter.loadEventDetail(eventId)
    }

    override fun setView() {
        val event = presenter.event

        event.addAll(event)
        val oldSdf = SimpleDateFormat("yyyy-MM-dd")
        val newSdf = SimpleDateFormat("EEEE, MMM d, yyyy", Locale.getDefault())
        val newDate = newSdf.format(oldSdf.parse(event[0].dateEventLocal))
        date.text = newDate
        home_name.text = event[0].strHomeTeam
        away_name.text = event[0].strAwayTeam
        goal_home.text = event[0].intHomeScore
        goal_away.text = event[0].intAwayScore
        shot_home.text = event[0].intHomeShots
        shot_away.text = event[0].intAwayShots
        keeper_home.text = event[0].strHomeLineupGoalkeeper
        keeper_away.text = event[0].strAwayLineupGoalkeeper
        defender_home.text = event[0].strHomeLineupDefense
        defender_away.text = event[0].strAwayLineupDefense
        mid_home.text = event[0].strHomeLineupMidfield
        mid_away.text = event[0].strAwayLineupMidfield
        forw_home.text = event[0].strHomeLineupForward
        forw_away.text = event[0].strAwayLineupForward
        sub_home.text = event[0].strHomeLineupSubstitutes
        sub_away.text = event[0].strAwayLineupSubstitutes
        getDataTeam()
    }

    override fun setBagde() {
        teams = presenter.teams
        if (teams[0].idTeam == presenter.event[0].idHomeTeam) {
            val imageHome = findViewById<ImageView>(R.id.img_team_home)
            Picasso.get().load(teams[0].strTeamBadge).into(imageHome)
            hideProgressBar()
        } else {
            val imageAway = findViewById<ImageView>(R.id.img_team_away)
            Picasso.get().load(teams[0].strTeamBadge).into(imageAway)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val intent = intent
        eventId = intent.getStringExtra("detail").toString()
        supportActionBar?.title = "Match Detail"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        presenter = MatchDetailPresenter(this, Scheduler())
        showProgessBar()
        getEventDetails()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.detail_menu, menu)
        itemFav = menu?.findItem(R.id.menu_item_fav)!!
        setFavorite(fav)
        presenter.checkFavorite(eventId)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.menu_item_fav) {
            if (fav == false) {
                presenter.insertFavorite(presenter.event)
                Snackbar.make(contentView!!, "Added to favorite", Snackbar.LENGTH_LONG).show()
            } else {

                presenter.deleteFavorite(presenter.event[0].idEvent!!)
                Snackbar.make(contentView!!, "Removed to favorite", Snackbar.LENGTH_LONG).show()
            }
        } else {
            finish()
        }
        return super.onOptionsItemSelected(item)

    }

    override fun setFavorite(isFav: Boolean) {
        fav = isFav
        if (fav) {
            itemFav.setIcon(R.drawable.ic_on_favorite)

        } else {
            itemFav.setIcon(R.drawable.ic_favorites)
        }

    }
}
