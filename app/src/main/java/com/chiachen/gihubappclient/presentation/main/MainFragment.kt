package com.chiachen.gihubappclient.presentation.main

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.chiachen.gihubappclient.R
import com.chiachen.gihubappclient.presentation.base.BaseFragment
import com.chiachen.gihubappclient.util.extension.*
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.fragment_main.view.*
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

    private var queryDisposable : Disposable? = null

    override fun onViewBound(view: View, savedInstanceState: Bundle?) {
        super.onViewBound(view, savedInstanceState)

        RxTextView.afterTextChangeEvents(view.editText)
            .map { it.view().text.isBlank() }
            .asLiveData()
            .subscribe(viewLifecycleOwner) { isEmpty ->
                view.clear.toggleVisibility(!isEmpty, true)
            }

    }

    override fun onResume() {
        super.onResume()

        clear.setOnClickListener { editText.setText("") }
        queryDisposable = RxTextView.afterTextChangeEvents(editText)
            .map { it.editable()!!.toString() }
            .filter { it.isBlank() || it.trim().length >= 2 }
            .subscribe(viewModel::setNewQuery, Throwable::printStackTrace)
    }

    override fun onPause() {
        super.onPause()
        queryDisposable.unsubscribe()
        clear.setOnClickListener(null)
    }

    override fun provideLayoutId(): Int = R.layout.fragment_main

}
