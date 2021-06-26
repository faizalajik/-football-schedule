package com.example.fak.football2.util

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class Scheduler : MainScheduler {
    override fun ui()= AndroidSchedulers.mainThread()

    override fun io() = Schedulers.io()

    override fun computation() = Schedulers.computation()

}