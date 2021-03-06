package com.chiachen.githubappclient.di

import android.app.ActivityManager
import android.content.Context
import android.util.Log
import androidx.annotation.Keep
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.executor.GlideExecutor
import com.bumptech.glide.load.engine.executor.GlideExecutor.UncaughtThrowableStrategy.IGNORE
import com.bumptech.glide.module.AppGlideModule
import com.bumptech.glide.request.RequestOptions

@GlideModule
@Keep
class GlideModule : AppGlideModule() {

    override fun applyOptions(context: Context, builder: GlideBuilder) {

        builder.setLogLevel(Log.ERROR)
                .setDefaultRequestOptions(defaultRequestOptions(context))
                .setDiskCacheExecutor(GlideExecutor.newDiskCacheExecutor(IGNORE))
                .setSourceExecutor(GlideExecutor.newSourceExecutor(IGNORE))
    }

    private fun defaultRequestOptions(context: Context): RequestOptions {
        val activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager

        return RequestOptions()
                // Prefer higher quality images unless we're on a low RAM device
                .format(if (activityManager.isLowRamDevice)
                    DecodeFormat.PREFER_RGB_565 else DecodeFormat.PREFER_ARGB_8888
                ).disallowHardwareConfig()
                .diskCacheStrategy(DiskCacheStrategy.DATA)
                .centerCrop()
    }

    override fun isManifestParsingEnabled(): Boolean = false

}