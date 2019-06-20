package com.chiachen.githubappclient.presentation.navigator

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.DefaultLifecycleObserver
import com.chiachen.githubappclient.R
import com.chiachen.githubappclient.presentation.main.MainFragment
import com.chiachen.githubappclient.util.extension.fragmentTransaction
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