package com.chiachen.githubappclient.util.extension

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData

fun <T> LiveData<T>.subscribe(lifecycleOwner: LifecycleOwner, func: (T) -> Unit) {
    this.observe(lifecycleOwner, androidx.lifecycle.Observer {
        if (it != null) {
            func(it)
        }
    })
}