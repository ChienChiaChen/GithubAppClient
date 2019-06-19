package com.chiachen.gihubappclient.util.rx

import io.reactivex.Scheduler

interface ISchedulersProvider {
    fun ui(): Scheduler
    fun io(): Scheduler
}