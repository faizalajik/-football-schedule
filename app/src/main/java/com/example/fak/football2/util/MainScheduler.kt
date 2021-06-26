package com.example.fak.football2.util

import io.reactivex.Scheduler

interface MainScheduler {
    fun ui() : Scheduler
    fun io() : Scheduler
    fun computation() : Scheduler
}