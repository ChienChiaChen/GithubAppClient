package com.chiachen.gihubappclient.api.github

import io.reactivex.Scheduler

interface ISchedulersProvider {
    fun ui(): Scheduler
    fun io(): Scheduler
}