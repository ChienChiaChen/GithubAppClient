package com.chiachen.gihubappclient

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.chiachen.gihubappclient.di.GlideApp

object BindingsAdapter {

    private const val OVERRIDE_SMALL = 150

    @JvmStatic
    @BindingAdapter("avatar")
    fun setImageUrl(view: ImageView, url: String) {
        val context = view.context
        GlideApp.with(context).clear(view)

        GlideApp.with(context)
            .load(url)
            .override(OVERRIDE_SMALL)
            .placeholder(R.drawable.ic_octoface)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(view)
    }

}