package com.chiachen.gihubappclient.presentation.main

import androidx.lifecycle.ViewModelProvider
import com.chiachen.gihubappclient.R
import com.chiachen.gihubappclient.presentation.base.BaseFragment
import com.chiachen.gihubappclient.util.extension.lazyFast
import com.chiachen.gihubappclient.util.extension.viewModelProvider
import javax.inject.Inject


class MainFragment : BaseFragment() {

    companion object {
        const val TAG = "MainFragment"

        @JvmStatic
        fun newInstance() = MainFragment()
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by lazyFast { viewModelProvider<MainFragmentViewModel>(viewModelFactory) }

    override fun provideLayoutId(): Int = R.layout.fragment_main

}
