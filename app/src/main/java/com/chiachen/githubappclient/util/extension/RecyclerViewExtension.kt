package com.chiachen.githubappclient.util.extension

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

fun androidx.recyclerview.widget.RecyclerView.addLoadMoreListener(func: () -> Unit) {
    addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            val adapter = recyclerView.adapter ?: return

            val layoutManager = recyclerView.layoutManager
            layoutManager?.let {
                val last = (it as LinearLayoutManager).findLastVisibleItemPosition()
                val count = adapter.itemCount
                if (last == count - 1) {
                    func()
                }
            }
        }
    })
}