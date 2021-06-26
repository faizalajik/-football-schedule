package com.example.fak.football2.network

import com.example.fak.football2.model.Event
import com.example.fak.football2.model.EventRespone
import com.example.fak.football2.model.TeamResponse
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiRequest {
    @GET("eventsnextleague.php?")
    fun nextEvent(@Query("id") id: String?): Observable<EventRespone>

    @GET("eventspastleague.php?")
    fun lastEvent(@Query("id") id: String?): Observable<EventRespone>

    @GET("lookupteam.php?")
    fun teamDetails(@Query("id") id: String?): Observable<TeamResponse>

    @GET("lookupevent.php?")
    fun eventDetails(@Query("id") id: String?): Observable<EventRespone>
}