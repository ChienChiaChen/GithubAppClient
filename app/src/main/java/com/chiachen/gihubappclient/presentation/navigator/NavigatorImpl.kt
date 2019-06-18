package com.chiachen.gihubappclient.presentation.navigator

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.DefaultLifecycleObserver
import com.chiachen.gihubappclient.R
import com.chiachen.gihubappclient.presentation.main.MainFragment
import com.chiachen.gihubappclient.util.extension.fragmentTransaction
import javax.inject.Inject

class NavigatorImpl @Inject constructor(private val activity: AppCompatActivity) : DefaultLifecycleObserver, Navigator {


    override fun toMainFragment() {
        activity.fragmentTransaction {
            setTransition(androidx.fragment.app.FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            val fragment = activity.supportFragmentManager.findFragmentByTag(MainFragment.TAG)
            if (fragment == null) {
                replace(R.id.fragmentContainer, MainFragment.newInstance(), MainFragment.TAG)
            } else {
                show(fragment)
            }
        }
    }
}