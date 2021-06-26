package com.example.fak.football2.util

import io.reactivex.schedulers.Schedulers

class TrampolineScheduler : MainScheduler {
    override fun ui() = Schedulers.trampoline()

    override fun io()= Schedulers.trampoline()

    override fun computation()= Schedulers.trampoline()
}