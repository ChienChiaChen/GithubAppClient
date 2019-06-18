package com.chiachen.gihubappclient

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.chiachen.gihubappclient.util.extension.setLightStatusBar

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setLightStatusBar()
        setContentView(R.layout.activity_main)
    }
}
