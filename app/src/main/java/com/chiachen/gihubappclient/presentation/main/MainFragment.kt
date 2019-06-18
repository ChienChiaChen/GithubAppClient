package com.chiachen.gihubappclient.presentation.main

import com.chiachen.gihubappclient.R
import com.chiachen.gihubappclient.presentation.base.BaseFragment


class MainFragment : BaseFragment() {

    companion object {
        const val TAG = "MainFragment"

        @JvmStatic
        fun newInstance() = MainFragment()
    }

    override fun provideLayoutId(): Int = R.layout.fragment_main

}
