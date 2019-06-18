package com.chiachen.gihubappclient.util.extension

import android.graphics.Color
import android.view.View
import android.view.Window

fun Window.setLightStatusBar() {
    decorView.systemUiVisibility = 0

    statusBarColor = Color.TRANSPARENT

    var flags = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)

    if (isMarshmallow()) {
        flags = flags or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR

        if (isOreo()) {
            flags = flags or View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
            navigationBarColor = Color.WHITE
        }
    }
    decorView.systemUiVisibility = flags
}

fun Window.removeLightStatusBar() {

    decorView.systemUiVisibility = 0

    statusBarColor = Color.TRANSPARENT

    var flags = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)


    if (isOreo()) {
        flags = flags or View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
        navigationBarColor = Color.WHITE
    }
    decorView.systemUiVisibility = flags
}