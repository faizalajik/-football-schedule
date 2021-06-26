package com.example.fak.football2

import com.example.fak.football2.helper.DatabaseHelper
import com.example.fak.football2.model.Event
import com.example.fak.football2.model.EventRespone
import com.example.fak.football2.model.Team
import com.example.fak.football2.model.TeamResponse
import com.example.fak.football2.network.ApiRequest
import com.example.fak.football2.presenter.Contract
import com.example.fak.football2.presenter.MatchDetailPresenter
import com.example.fak.football2.presenter.MatchPresenter
import com.example.fak.football2.util.MainScheduler
import com.example.fak.football2.util.TrampolineScheduler
import com.example.fak.football2.view.DetailMatchActivity
import com.example.fak.football2.view.fragment.FavoriteFragment
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.*
import org.mockito.Mockito.*

class PresenterTest {
    @Mock
    private lateinit var apiRequest: ApiRequest
    @Mock
    private lateinit var matchView: Contract.MatchView
    @Mock
    private lateinit var view: DetailMatchActivity
    @Mock
    private lateinit var scheduler: MainScheduler
    @Mock
    private lateinit var matchDetail: Contract.MatchDetail
    @Mock
    private lateinit var matchDetailPresenter: MatchDetailPresenter
    @Mock
    private lateinit var matchPresenter: MatchPresenter
    @Mock
    private lateinit var db: DatabaseHelper

    private var event = arrayListOf<Event>()
    private var team = arrayListOf<Team>()
    @Mock
    private lateinit var eventObservable: Observable<EventRespone>
    @Mock
    private lateinit var teamsObservable: Observable<TeamResponse>
    @Mock
    private lateinit var eventRespone: EventRespone
    @Mock
    private lateinit var teamsRespone: TeamResponse

    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)

        eventRespone = EventRespone(event)
        teamsRespone = TeamResponse(team)
        scheduler = TrampolineScheduler()
        matchPresenter = MatchPresenter(scheduler, matchView)
        eventObservable = Observable.just(eventRespone)
        teamsObservable = Observable.just(teamsRespone)
        matchDetail = DetailMatchActivity()
        matchDetailPresenter = MatchDetailPresenter(view, scheduler)
        db = DatabaseHelper(view)
    }

    @Test
    fun matchDetailPresenter() {
        `when`(apiRequest.eventDetails("441613")).thenReturn(eventObservable)
        matchDetailPresenter.loadEventDetail("441613")
        verify(view, times(1)).setView()

    }

    @Test
    fun match() {
        `when`(apiRequest.nextEvent("4332")).thenReturn(eventObservable)
        `when`(apiRequest.lastEvent("4332")).thenReturn(eventObservable)
        matchPresenter.loadNextMatch()
        matchPresenter.loadLastMatch()
        verify(matchView, times(2)).setView()
    }

    @Test
    fun team() {
        `when`(apiRequest.teamDetails("133604")).thenReturn(teamsObservable)
        matchDetailPresenter.loadDataTeam("133604")
        verify(view, times(1)).setBagde()
    }
}



