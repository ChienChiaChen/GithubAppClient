package com.chiachen.githubappclient.util.extension

import android.view.View
import androidx.recyclerview.widget.RecyclerView

fun <T> RecyclerView.ViewHolder.setOnClickListener(
    data: List<T>,
    func: (item: T, position: Int, view: View) -> Unit
) {
    this.itemView.setOnClickListener {
        if (RecyclerView.NO_POSITION != adapterPosition) {
            data[adapterPosition]?.let { model -> func(model, adapterPosition, it) }
        }
    }
}