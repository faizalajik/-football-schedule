package com.example.fak.football2.presenter

import com.example.fak.football2.model.Event
import com.example.fak.football2.network.ApiRequest
import com.example.fak.football2.network.ConfigRetro
import com.example.fak.football2.util.MainScheduler

class MatchPresenter(val scheduler: MainScheduler, val v1: Contract.MatchView) : Contract.MatchPresenter {
    val event = arrayListOf<Event>()
    override fun loadNextMatch() {

        val service: ApiRequest = ConfigRetro.config()
            .create(ApiRequest::class.java)
        service.nextEvent("4332").subscribeOn(scheduler.io())
            .observeOn(scheduler.ui())
            .subscribe { it ->
                event.clear()
                event.addAll(it.events!!)
                v1.setView()
            }
    }


    override fun loadLastMatch() {
        val service: ApiRequest = ConfigRetro.config()
            .create(ApiRequest::class.java)
        service.lastEvent("4328").subscribeOn(scheduler.io())
            .observeOn(scheduler.ui())
            .subscribe { it ->
                event.clear()
                event.addAll(it.events!!)
                v1.setView()
            }
    }
}