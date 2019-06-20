package com.chiachen.githubappclient.presentation.main

import android.os.Bundle
import com.chiachen.githubappclient.R
import com.chiachen.githubappclient.presentation.navigator.Navigator
import com.chiachen.githubappclient.util.extension.setLightStatusBar
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setLightStatusBar()
        setContentView(R.layout.activity_main)

        if (null == savedInstanceState) {
            navigator.toMainFragment()
        }
    }
}
